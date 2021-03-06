package com.example.magentotest.Retrofit

import com.example.magentotest.Model.CategorieForAdding.CategorieForAdding
import com.example.magentotest.Model.CategoryPojo
import com.example.magentotest.Model.Credentials
import com.example.magentotest.Model.ImageForAdding.ImageForAdding
import com.example.magentotest.Model.Product.Product
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Model.ProductForAdding.ProductPojo
import retrofit2.Call
import retrofit2.http.*

interface Interface_API {


    @POST("rest/V1/integration/admin/token")
    @Headers("Content-Type: application/json")
    fun authentication(@Body credentials: Credentials): Call<String>


    @GET("rest/V1/products/{productSKU}")
    fun getProductbySKU(@Path("productSKU") productSKU: String, @Header("Authorization") token: String): Call<Product>

    @GET("rest/V1/products/")
    fun getAllProducts(@Query("searchCriteria") page: Int, @Header("Authorization") token: String): Call<ProductList>


    @POST("rest/V1/products/")
    @Headers("Content-Type: application/json")
    fun addProduct(@Body productPojo: ProductPojo, @Header("Authorization") token: String): Call<ProductList>

    @PUT("rest/V1/products/{productSKU}")
    @Headers("Content-Type: application/json")
    fun updateProduct(@Path("productSKU") productSKU: String, @Body productPojo: ProductPojo, @Header("Authorization") token: String): Call<ProductList>


    @POST("rest/V1/products/{productSKU}/media")
    @Headers("Content-Type: application/json")
    fun addImage(@Path("productSKU") productSKU: String, @Body image: ImageForAdding, @Header("Authorization") token: String): Call<Int>


    @GET(" rest/all/V1/categories")
    fun getAllCategories(@Header("Authorization") token: String): Call<CategoryPojo>

    @GET(" rest/all/V1/categories/{id}")
    @Headers("Content-Type: application/json")
    fun getCategorieById(@Path("id") id: Int, @Header("Authorization") token: String): Call<CategoryPojo>

    @POST("rest/V1/categories")
    @Headers("Content-Type: application/json")
    fun addCategory(@Body categoryPojo: CategorieForAdding, @Header("Authorization") token: String): Call<CategoryPojo>


}