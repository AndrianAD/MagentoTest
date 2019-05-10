package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.RoomAPI
import com.example.magentotest.data.CategoryPojo

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    private var productDB: ProductsRoomDatabase
    var livedataCategory: MutableLiveData<CategoryPojo> = MutableLiveData()

    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }


    fun getAllCategories() {
        retrofitAPI.getAllCategories(livedataCategory)
    }

    override fun onCleared() {
        super.onCleared()
    }
}