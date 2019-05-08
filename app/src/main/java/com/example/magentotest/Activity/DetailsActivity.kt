package com.example.magentotest.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.magentotest.*
import com.example.magentotest.Utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    companion object {
        var EXTRA_PRODUCT_SKU= "EXTRA_PRODUCT_SKU"
    }

    lateinit var productDB: ProductsRoomDatabase
    lateinit var productDao: ProductDAO





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        productDB = ProductsRoomDatabase.getInstance(this)!!
        productDao = productDB.userDao()

        var sku = intent.getStringExtra(EXTRA_PRODUCT_SKU)
        var productWithImage=productDao.getProductWithImagesbySKU(sku)


        tv_name_details.text = productWithImage.productRoom.name
        tv_price_details.text = productWithImage.productRoom.price.toString()


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
}
