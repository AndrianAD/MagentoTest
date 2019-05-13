package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.CategoryPojo
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

    override fun insertCategory(category: CategorieForAdding, callback: MutableLiveData<Boolean>) {
        retrofit!!.addCategory(category,"Bearer ${App.token}").enqueue(object : Callback<CategoryPojo>{
            override fun onFailure(call: Call<CategoryPojo>, t: Throwable) {
                Log.e("Error ADDCategories", t.message)
                callback.postValue(false)

            }

            override fun onResponse(call: Call<CategoryPojo>, response: Response<CategoryPojo>) {
                Log.i("OK - ADDCategories", response.body().toString())
                callback.postValue(true)
            }
        })

    }

    override fun getAllCategories(callback: MutableLiveData<CategoryPojo>) {
        retrofit!!.getAllCategories("Bearer ${App.token}").enqueue(object : Callback<CategoryPojo> {
            override fun onFailure(call: Call<CategoryPojo>, t: Throwable) {
                Log.e("Error getCategories", t.message)
            }

            override fun onResponse(call: Call<CategoryPojo>, response: Response<CategoryPojo>) {
                Log.i("OK - getCategories", response.body().toString())
                callback.postValue(response.body())
            }
        })

    }

    override fun updateProduct(
        livedata: MutableLiveData<Boolean>,
        sku: String,
        product: ProductForAdding,
        selectedImage: String
    ) {
        retrofit!!.updateProduct(sku, ProductPojo(product), "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {
                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)
                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.i("retrofit", response.body().toString())
                    var image = ImageForAdding(Entry(content = Content(selectedImage, product.name + "foto")))
                    retrofit!!.addImage(sku, image, "Bearer ${App.token}").enqueue(object : Callback<Int> {
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.e("retrofit", t.message)
                        }

                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
                            Log.i("retrofit", response.body().toString())
                            livedata.postValue(true)

                        }
                    }
                    )
                }

            })
    }

    override fun insertProduct(livedata: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String) {

        retrofit!!.addProduct(ProductPojo(product), "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {
                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)

                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.i("retrofit", response.body().toString())

                    var image = ImageForAdding(Entry(content = Content(selectedImage, product.name + "foto")))

                    retrofit!!.addImage(product.sku, image, "Bearer ${App.token}").enqueue(object : Callback<Int> {
                        override fun onFailure(call: Call<Int>, t: Throwable) {
                            Log.e("retrofit", t.message)
                        }

                        override fun onResponse(call: Call<Int>, response: Response<Int>) {
                            Log.e("retrofit", response.body().toString())
                            livedata.postValue(true)
                        }
                    }
                    )
                }
            })
    }


    override fun insertImage(viewModel: UserViewModel) {

    }

    override fun getAllProduct(viewModel: UserViewModel) {

        retrofit!!.getAllProducts(1, "Bearer ${App.token}")
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