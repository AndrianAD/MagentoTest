package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.RoomAPI
import com.example.magentotest.data.CategoryPojo
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class UploadProductViewModel(application: Application) : AndroidViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    var callbackUpdateLivedata = MutableLiveData<Boolean>()
    var callbackInsertLivedata = MutableLiveData<Boolean>()
    var allCategories: MutableLiveData<CategoryPojo> = MutableLiveData()

    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }

    fun insertProduct(livedata: MutableLiveData<Boolean>,product: ProductForAdding, selectedImage: String) {
        retrofitAPI.insertProduct(livedata,product, selectedImage)
    }


    fun updateProduct(livedata: MutableLiveData<Boolean>, sku:String, product: ProductForAdding, selectedImage: String) {
       retrofitAPI.updateProduct(livedata,sku,product,selectedImage)
    }

    fun getAllCategories() {
        retrofitAPI.getAllCategories(allCategories)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
