package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.*
import com.example.magentotest.Model.CategorieForAdding.CategorieForAdding
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Utils.Utils

class UserViewModel(application: Application) : AndroidViewModel(application) {

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


    fun insertImageToDB(productList: ProductList) {
        roomAPI.insertImage(productList)
    }


    fun insertCategoryRetrofit(category: CategorieForAdding) {
        retrofitAPI.insertCategory(category, callbackAddingCategory)
    }

    fun insertCategoryRoom(productList: ProductList) {
        roomAPI.insertCategory(productList)
    }




    fun saveProductToDb() {
        Log.e("Magento", "saveToDB, " + ", thread: " + Thread.currentThread().name)
        val listOfProductRoom = Utils.convertProductsToProductsRoom(productsList.value!!)
        roomAPI.insertProductRoomList(listOfProductRoom)
    }


    override fun onCleared() {
        super.onCleared()
    }

}