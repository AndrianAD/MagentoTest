package com.example.magentotest

import android.app.Application
import android.content.Context
import com.example.magentotest.Room.Model.ProductWithImagesAndCategory
import com.facebook.stetho.Stetho

val Context.myApp: App get() = applicationContext as App


class App : Application() {




    companion object {
        var productWithImageAndCategories: List<ProductWithImagesAndCategory> = listOf()
        var token: String = ""
        lateinit var DataBASE:ProductsRoomDatabase

    }


    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        DataBASE = ProductsRoomDatabase.getInstance(this)!!

    }

    fun getDataBase(): ProductsRoomDatabase {
        return DataBASE!!
    }
}