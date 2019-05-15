package com.example.magentotest.Activity

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.magentotest.Activity.ViewModels.UploadProductViewModel
import com.example.magentotest.Model.CategoryPojo
import com.example.magentotest.Model.Product.CategoryLink
import com.example.magentotest.Model.Product.ExtensionAttributes
import com.example.magentotest.Model.ProductForAdding.ProductForAdding
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.R
import kotlinx.android.synthetic.main.activity_upload_product.*
import kotlinx.android.synthetic.main.dialog_category.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*


class UploadProductActivity : AppCompatActivity() {

    val uploadProductViewModel: UploadProductViewModel by lazy {
        ViewModelProviders.of(this).get(UploadProductViewModel::class.java)
    }
    var selectedImage: String? = null
    var isEditind: Boolean = false
    lateinit var productDB: ProductsRoomDatabase
    lateinit var productDao: ProductDAO
    var sku: String = ""
    var listOfCategory = ArrayList<CategoryPojo>()
    var namesOfCategories = ArrayList<String>()
    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        productDB = ProductsRoomDatabase.getInstance(this)!!
        productDao = productDB.userDao()

        isEditind = intent.hasExtra(DetailsActivity.EXTRA_PRODUCT_SKU)

        uploadProductViewModel.productsList.observe(this, Observer {
            uploadProductViewModel.saveProductToDb(it!!)
            uploadProductViewModel.insertImageToDB(it!!)
            finish()
        })

        //Editing
        if (isEditind == true) {

            sku = intent.getStringExtra(DetailsActivity.EXTRA_PRODUCT_SKU)
            val productWithImage = productDao.getProductWithImagesbySKU(sku)
            et_name.setText(productWithImage.productRoom.name)
            et_price.setText(productWithImage.productRoom.price.toString())
        }

        button_apply.setOnClickListener {
            //Editing
            if (isEditind == true) {
                val product = ProductForAdding(
                    name = et_name.text.toString(),
                    price = et_price.text.toString().toDouble(),
                    sku = et_name.text.toString()
                )
                uploadProductViewModel.updateProduct(
                    uploadProductViewModel.callbackUpdateLivedata,
                    sku,
                    product,
                    selectedImage
                )
                //Inserting
            } else {
                val product = ProductForAdding(
                    name = et_name.text.toString(),
                    price = et_price.text.toString().toDouble(),
                    sku = et_name.text.toString()
                )
                uploadProductViewModel.insertProduct(
                    uploadProductViewModel.callbackInsertLivedata,
                    product,
                    selectedImage
                )

            }
        }

        uploadProductViewModel.callbackUpdateLivedata.observe(this, Observer {
            if (it == true)
                uploadProductViewModel.getAllProducts()
        })
        uploadProductViewModel.callbackInsertLivedata.observe(this, Observer {
            if (it == true)
                uploadProductViewModel.getAllProducts()
        })

        uploadProductViewModel.allCategories.observe(this, Observer {
            сategoriesToList(it!!)
            getNamesOfCategories(it!!)
        })
        uploadProductViewModel.getAllCategories()


        button_attach.setOnClickListener {

            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

        btn_add_category.setOnClickListener {

            var dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_category)
            dialog.show()
            dialog.spinner.adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                namesOfCategories
            )
            var clickedItem:Int=0
            dialog.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.i("Click", "positions $position")
                    Log.i("Click", "id ${listOfCategory.get(position).id}")
                    clickedItem=position
                }
            }
            dialog.button_OK.setOnClickListener {
                dialog.dismiss()
                val product = ProductForAdding(
                    name = et_name.text.toString(),
                    price = et_price.text.toString().toDouble(),
                    sku = et_name.text.toString(),
                    extension_attributes = ExtensionAttributes(
                        category_links = listOf(CategoryLink(listOfCategory.get(clickedItem).id.toString()))
                    )
                )
                uploadProductViewModel.updateProduct(
                    uploadProductViewModel.callbackUpdateLivedata,
                    sku,
                    product,
                    selectedImage
                )

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val imageUri: Uri = data.data
                var bitmap: Bitmap? = null
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                selectedImage = bitmapToBase64(bitmap!!)
                tv_attach_foto.setText("Foto is selected")
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }

    fun сategoriesToList(categorie: CategoryPojo) {
        listOfCategory.add(categorie)
        for (item in categorie.children_data) {
            сategoriesToList(item)
        }
    }

    fun getNamesOfCategories(categorie: CategoryPojo) {
        namesOfCategories.add(categorie.name)
        for (item in categorie.children_data) {
            getNamesOfCategories(item)
        }
    }
}




