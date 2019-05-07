package com.example.magentotest.Activity

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import com.example.magentotest.App
import com.example.magentotest.UploadProductViewModel
import com.example.magentotest.data.ProductForAdding.ProductForAdding
import kotlinx.android.synthetic.main.activity_upload_product.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException


class UploadProductActivity : AppCompatActivity() {

    val uploadProductViewModel: UploadProductViewModel by lazy {
        ViewModelProviders.of(this).get(UploadProductViewModel::class.java)
    }
    var selectedImage: String? = null
    var position: Int = 0
    var isEditind: Boolean?=null

    private val PICK_IMAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.magentotest.R.layout.activity_upload_product)


        var products = App.productWithImages

        if (intent.hasExtra("position")) {
            position = intent.getStringExtra("position").toInt()
            et_name.setText(products.get(position).productRoom.name)
            et_price.setText(products.get(position).productRoom.price.toString())
        }


        button_OK.setOnClickListener {

            var product = ProductForAdding(
                name = et_name.text.toString(),
                price = et_price.text.toString().toInt(),
                sku = et_name.text.toString(),
                weight = 20
            )

            if (!selectedImage.isNullOrEmpty()) {
                uploadProductViewModel.insertProduct(product, selectedImage!!)
            }
        }


        button_attach.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
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


}




