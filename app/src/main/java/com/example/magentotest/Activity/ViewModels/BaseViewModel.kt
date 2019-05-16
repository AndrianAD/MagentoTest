package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.RoomAPI
import com.example.magentotest.Utils.Utils

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()

    fun saveProduct(productsList: ProductList) {

        Log.i("Magento", "saveProductsAndImagesToDB, " + ", thread: " + Thread.currentThread().name)
        val listOfProductRoom = Utils.convertProductsToProductsRoom(productsList)
        roomAPI.insertProductRoomList(listOfProductRoom)
        roomAPI.insertImage(productsList)
        roomAPI.insertCategory(productsList)
    }
    fun clearDataBase() {
        roomAPI.productDao.deleteAllProducts()
        roomAPI.productDao.deleteAllImages()
        roomAPI.productDao.deleteAllCategories()
    }
}