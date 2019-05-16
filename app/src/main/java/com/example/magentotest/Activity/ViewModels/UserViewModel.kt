package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.*
import com.example.magentotest.Model.CategorieForAdding.CategorieForAdding
import com.example.magentotest.Model.Product.ProductList

class UserViewModel(application: Application) : BaseViewModel(application) {

    var productsList = MutableLiveData<ProductList>()
    var productWithImage = MutableLiveData<Boolean>()
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO
    var callbackAddingCategory: MutableLiveData<Boolean> = MutableLiveData()

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }

    fun getAllProduct(type: String) {
        Log.i(
            "Magento", "getAllProduct, type: "
                    + type + ", thread: " + Thread.currentThread().name
        )
        when (type) {
            "Retrofit" -> retrofitAPI.getAllProductsFromServer(productsList)
            "Room" -> roomAPI.getAllProductFromRoom(this)
        }
    }

    fun insertCategoryRetrofit(category: CategorieForAdding) {
        retrofitAPI.insertCategory(category, callbackAddingCategory)
    }
    override fun onCleared() {
        super.onCleared()
    }

}