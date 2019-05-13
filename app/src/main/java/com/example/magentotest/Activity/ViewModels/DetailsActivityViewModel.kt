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
    var allCategories: MutableLiveData<CategoryPojo> = MutableLiveData()
    var idCategory: MutableLiveData<CategoryPojo> = MutableLiveData()

    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }

    fun getCategoryById(id: Int){
        retrofitAPI.getCategoryById(id,idCategory)
    }

    fun getAllCategories() {
        retrofitAPI.getAllCategories(allCategories)
    }

    override fun onCleared() {
        super.onCleared()
    }
}