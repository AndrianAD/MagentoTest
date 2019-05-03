package com.example.magentotest.Activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.magentotest.ProductViewModel
import com.example.magentotest.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var position = intent.getStringExtra("position").toInt()
        tv_name_details.text="Hello $position"


      //var product = let{productViewModel.productsListLIVE.value!!.items.get(position)}

    }
}
