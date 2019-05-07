package com.example.magentotest

import android.util.Log
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.data.ImageForAdding.Content
import com.example.magentotest.data.ImageForAdding.Entry
import com.example.magentotest.data.ImageForAdding.ImageForAdding
import com.example.magentotest.data.Product.ProductList
import com.example.magentotest.data.ProductForAdding.ProductForAdding
import com.example.magentotest.data.ProductForAdding.ProductPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitAPI : BaseAPI {
    var retrofit = RetrofitFactory.retrofitInstance


    override fun insertProduct(product: ProductForAdding, selectedImage: String) {

        retrofit!!.addProduct(ProductPojo(product), "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {
                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)

                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.e("retrofit", response.body().toString())

                    var image = ImageForAdding(Entry(content = Content(selectedImage, product.name + "foto")))

                    retrofit!!.addImage(product.sku, image, "Bearer ${App.token}").enqueue(object : Callback<Int> {
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.e("retrofit", t.message)

                        }
                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
                            Log.e("retrofit", response.body().toString())
                        }
                    })
                }
            })
    }


    override fun insertImage(viewModel: ProductViewModel) {

    }

    override fun getAllProduct(viewModel: ProductViewModel) {

        retrofit!!.getAllProducts(1, "Bearer ${viewModel.tokenLIVE.value}")
            .enqueue(object : Callback<ProductList> {

                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)
                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.v("retrofit", response.message())
                    viewModel.productsList = response.body()!!
                    viewModel.saveProductToDb()
                }
            })
    }
}