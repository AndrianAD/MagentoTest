package com.example.magentotest

import android.app.Application
import android.content.Context
import com.example.magentotest.Room.Model.ProductWithImages
import com.facebook.stetho.Stetho

val Context.myApp: App
    get() = applicationContext as App
class App: Application() {

    companion object {
        var productWithImages: List<ProductWithImages> = listOf()
        lateinit var token:String
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

    }
}