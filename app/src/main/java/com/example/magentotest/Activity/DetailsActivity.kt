package com.example.magentotest.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.magentotest.App
import com.example.magentotest.R
import com.example.magentotest.Utils.imageBaseURL
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var products=App.productWithImages
        var position = intent.getStringExtra("position").toInt()
        tv_name_details.text = products.get(position).productRoom.name
        tv_price_details.text= products.get(position).productRoom.price.toString()


        var finalUrl: Any
        if(products.get(position).images.size>0) {
            var file: String = products.get(position).images.get(0).pathImage
            finalUrl = "$imageBaseURL$file"

        }else{
            finalUrl = R.drawable.no_image_available
        }
        Glide.with(applicationContext)
            .load(finalUrl)
            .error(R.drawable.no_image_available)
            .into(imageView_details)

    }
}
