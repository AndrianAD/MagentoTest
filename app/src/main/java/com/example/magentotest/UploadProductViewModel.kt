package com.example.magentotest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class UploadProductViewModel(application: Application) : AndroidViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    var callbackUpdateLivedata = MutableLiveData<Boolean>()

    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }

    fun insertProduct(product: ProductForAdding, selectedImage: String) {
        retrofitAPI.insertProduct(product, selectedImage)
    }


    fun updateProduct(livedata: MutableLiveData<Boolean>, sku:String, product: ProductForAdding, selectedImage: String) {
       retrofitAPI.updateProduct(livedata,sku,product,selectedImage)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
