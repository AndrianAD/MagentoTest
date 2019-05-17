package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Model.CategorieForAdding.CategorieForAdding
import com.example.magentotest.Model.CategoryPojo
import com.example.magentotest.Model.ImageForAdding.Content
import com.example.magentotest.Model.ImageForAdding.Entry
import com.example.magentotest.Model.ImageForAdding.ImageForAdding
import com.example.magentotest.Model.Product.Product
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Model.ProductForAdding.ProductForAdding
import com.example.magentotest.Model.ProductForAdding.ProductPojo
import com.example.magentotest.Retrofit.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitAPI {

    var retrofit = RetrofitFactory.retrofitInstance


    fun getProductbySKU(sku: String, callback: MutableLiveData<Product>) {
        retrofit!!.getProductbySKU(sku, "Bearer ${App.token}").enqueue(object : Callback<Product> {
            override fun onFailure(call: Call<Product>, t: Throwable) {
                Log.e("Error getProductbySKU", t.message)
            }

            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Log.i("OK - getProductbySKU", response.body().toString())
            }
        })
    }

    fun getNameCategoryById(id: Int, callback:(String) -> Unit) {
        retrofit?.getCategorieById(id, "Bearer ${App.token}")?.enqueue(object : Callback<CategoryPojo> {
            override fun onFailure(call: Call<CategoryPojo>, t: Throwable) {
                Log.e("Error getCategoriesById", t.message)
            }

            override fun onResponse(call: Call<CategoryPojo>, response: Response<CategoryPojo>) {
                Log.i("OK - getCategoriesById", response.body().toString())
                callback.invoke(response.body()!!.name)
            }
        })
    }

    fun insertCategory(category: CategorieForAdding, callback: MutableLiveData<Boolean>) {
        retrofit!!.addCategory(category, "Bearer ${App.token}").enqueue(object : Callback<CategoryPojo> {
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

    fun getAllCategories(callback: MutableLiveData<CategoryPojo>) {
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

    fun updateProduct(
        callback: MutableLiveData<Boolean>,
        sku: String,
        product: ProductForAdding,
        selectedImage: String?
    ) {
        retrofit!!.updateProduct(sku, ProductPojo(product), "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {
                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)
                    callback.postValue(false)
                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.i("retrofit", response.body().toString())
                    if (selectedImage == null) {
                        callback.postValue(true)
                    } else {
                        attachImageToProduct(callback, product, selectedImage)
                    }
                }
            })
    }

    fun insertProduct(callback: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String?) {

        retrofit!!.addProduct(ProductPojo(product), "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {
                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("retrofit", t.message)

                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.i("retrofit", response.body().toString())
                    if (selectedImage == null) {
                        callback.postValue(true)
                    } else {
                        attachImageToProduct(callback, product, selectedImage)
                    }
                }
            })
    }


    fun attachImageToProduct(callback: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String) {
        var image = ImageForAdding(Entry(content = Content(selectedImage, product.name + "foto")))
        retrofit!!.addImage(product.sku, image, "Bearer ${App.token}").enqueue(object : Callback<Int> {
            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("retrofit", t.message)
            }

            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                Log.e("retrofit", response.body().toString())
                callback.postValue(true)
            }
        }
        )
    }

    fun getAllProductsFromServer(callback: MutableLiveData<ProductList>) {
        retrofit!!.getAllProducts(1, "Bearer ${App.token}")
            .enqueue(object : Callback<ProductList> {

                override fun onFailure(call: Call<ProductList>, t: Throwable) {
                    Log.e("Error: ", t.message)
                }

                override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                    Log.i("Response : getAllProductsFromServer - OK ", response.message())
                    callback.postValue(response.body())
                }
            })
    }
}