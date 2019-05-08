package com.example.magentotest.Retrofit

import com.example.magentotest.data.Credentials
import com.example.magentotest.data.ImageForAdding.ImageForAdding
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import com.example.magentotest.data.ProductForAdding.ProductPojo
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

}