package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.RoomAPI

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    private var productDB: ProductsRoomDatabase

    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }

    override fun onCleared() {
        super.onCleared()
    }
}