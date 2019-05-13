package com.example.magentotest.Activity

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.magentotest.Activity.ViewModels.DetailsActivityViewModel
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.R
import com.example.magentotest.Utils.imageBaseURL
import com.example.magentotest.data.CategoryPojo
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.dialog_category.*
import java.util.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PRODUCT_SKU = "EXTRA_PRODUCT_SKU"
    }

    val detailsActivityViewModel: DetailsActivityViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsActivityViewModel::class.java)
    }
    lateinit var productDB: ProductsRoomDatabase
    lateinit var productDao: ProductDAO
    lateinit var categoryObserver: Observer<CategoryPojo>
    var listOfCategory = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        productDB = ProductsRoomDatabase.getInstance(this)!!
        productDao = productDB.userDao()


        var sku = intent.getStringExtra(EXTRA_PRODUCT_SKU)
        var productWithImage = productDao.getProductWithImagesbySKU(sku)


        tv_name_details.text = "name:  "  + productWithImage.productRoom.name
        tv_price_details.text = "price:  " + productWithImage.productRoom.price.toString() + "$"

        var finalUrl: Any
        if (productWithImage.images.size > 0) {
            var file: String = productWithImage.images.get(0).pathImage
            finalUrl = "$imageBaseURL$file"

        } else {
            finalUrl = R.drawable.no_image_available
        }
        Glide.with(applicationContext)
            .load(finalUrl)
            .error(R.drawable.no_image_available)
            .into(imageView_details)


        categoryObserver = Observer {
            сategoriesToList(it!!)
        }
        detailsActivityViewModel.livedataCategory.observe(this, categoryObserver)

        detailsActivityViewModel.getAllCategories()


        button_edit.setOnClickListener {
            //TODO: check if not null sku
            var intent = Intent(this@DetailsActivity, UploadProductActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT_SKU, sku)
            startActivity(intent)
        }

        button_categorie.setOnClickListener {

            var dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.dialog_category)
            dialog.show()
            var spinerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listOfCategory
            )
            dialog.spinner.adapter = spinerAdapter
            dialog.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.i("Click", "positions $position")
                }
            }
            dialog.button_OK.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    fun сategoriesToList(categorie: CategoryPojo) {
        listOfCategory.add(categorie.name)
        for (item in categorie.children_data) {
            сategoriesToList(item)
        }
    }
}

