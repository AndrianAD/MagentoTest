package com.example.magentotest.Activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.magentotest.Adapter.ProductAdapter
import com.example.magentotest.ProductViewModel
import com.example.magentotest.R
import com.example.magentotest.Room.Model.ProductRoom
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private val productViewModel: ProductViewModel by lazy {
        ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }
    lateinit var tokenObserver: Observer<String>
    lateinit var listofProductsRoomObserver: Observer<List<ProductRoom>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        productViewModel.tokenLIVE.value = intent.getStringExtra("Token")
        recyclerView.layoutManager = LinearLayoutManager(this)

tv_title.setOnClickListener { productViewModel.saveImages(productViewModel.productsList) }


        tokenObserver = Observer {

            Observable.fromCallable {
                productViewModel.gelAllProduct("Room")
                productViewModel.gelAllProduct("Retrofit")
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(this, "WELCOME", Toast.LENGTH_LONG).show()
                }
        }

        listofProductsRoomObserver = Observer {
            if (it != null) {

                val productAdapter = ProductAdapter(it)
                recyclerView.adapter = productAdapter

            }
        }



        productViewModel.tokenLIVE.observe(this, tokenObserver)
        productViewModel.productsRoomLIVE.observe(this, listofProductsRoomObserver)
    }


}


//        retrofit!!.getProductbySKU("cat", "Bearer $token").enqueue(object : Callback<Product> {
//
//            override fun onFailure(call: Call<Product>, t: Throwable) {
//                Log.v("retrofit", "Erorr $t.message")
//            }
//
//            override fun onResponse(call: Call<Product>, response: Response<Product>) {
//                Log.v("retrofit", response.message())
//                var product : Product? = response.body()
//                price.text=product!!.price.toString()
//                name.text=product!!.name
//            }
//        })