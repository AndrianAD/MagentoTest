package com.example.magentotest

import android.util.Log
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.data.Product.ProductList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitAPI : BaseAPI {




    var retrofit = RetrofitFactory.retrofitInstance

    override fun getAllProduct(viewModel: ProductViewModel) {

        retrofit!!.getAllProducts(1, "Bearer ${viewModel.tokenLIVE.value}")
            .enqueue(object : Callback<ProductList> {

                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)
                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.v("retrofit", response.message())
                    viewModel.productsList= response.body()!!
                    viewModel.saveToDb()
                }
            })
    }
}