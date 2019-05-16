package com.example.magentotest.Activity

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.magentotest.Activity.ViewModels.DetailsActivityViewModel
import com.example.magentotest.App
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.R
import com.example.magentotest.Utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PRODUCT_SKU = "EXTRA_PRODUCT_SKU"
    }

    val detailsActivityViewModel: DetailsActivityViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsActivityViewModel::class.java)
    }
    lateinit var productDB: ProductsRoomDatabase
    lateinit var productDao: ProductDAO
    var categoriesId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        productDB = App.DataBASE
        productDao = productDB.userDao()

    }

    override fun onResume() {
        super.onResume()
        var categoriesId = ""

        var sku = intent.getStringExtra(EXTRA_PRODUCT_SKU)
        var productWithImage = productDao.getProductWithImagesbySKU(sku)
        tv_name_details.text = "name:  " + productWithImage.productRoom.name
        tv_price_details.text = "price:  " + productWithImage.productRoom.price.toString() + "$"

        for(category in productWithImage.categories){
            categoriesId+="${category.categoryId}     "
        }
        tv_categories.text=categoriesId






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

        button_edit.setOnClickListener {
            //TODO: check if not null sku
            var intent = Intent(this@DetailsActivity, UploadProductActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT_SKU, sku)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
    }
}

