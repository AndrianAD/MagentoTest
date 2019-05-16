package com.example.magentotest.Activity.ViewModels


import android.app.Application
import com.example.magentotest.App
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI

class DetailsActivityViewModel(application: Application) : BaseViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }


    override fun onCleared() {
        super.onCleared()
    }
}