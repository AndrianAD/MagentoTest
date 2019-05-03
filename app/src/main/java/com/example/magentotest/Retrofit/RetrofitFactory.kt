package com.example.magentotest.Retrofit

import com.example.magentotest.Utils.baseURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object RetrofitFactory {
    private var retrofit: Interface_API? = null


    val retrofitInstance: Interface_API?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Interface_API::class.java)
            }
            return retrofit
        }


    fun getClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return httpClient.addNetworkInterceptor(logging).build()
    }
}