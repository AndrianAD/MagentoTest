package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.*
import com.example.magentotest.Model.Product.Product

class DetailsActivityViewModel(application: Application) : AndroidViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    private var productDB: ProductsRoomDatabase
    var callbackGetProductbySKU = MutableLiveData<Product>()
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }

    fun getProductbySKU(sku: String) {
        retrofitAPI.getProductbySKU(sku, callbackGetProductbySKU)
    }

    override fun onCleared() {
        super.onCleared()
    }
}