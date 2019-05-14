package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.*
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var productsList: ProductList
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
            "Retrofit" -> retrofitAPI.getAllProduct(this)
            "Room" -> roomAPI.getAllProduct(this)
        }
    }

    fun saveProductToDb() {
        Log.e("Magento", "saveToDB, " + ", thread: " + Thread.currentThread().name)
        roomAPI.insertImage(this)
        val listofProductRoom = convertProductsToProductsRoom(productsList)
        GlobalScope.launch(Dispatchers.Default) {
            for (item in listofProductRoom) {
                productDao.insert(item)
            }
            getAllProduct("Room")
        }
    }

    fun insertCategory(api: BaseAPI, category: CategorieForAdding) {
        api.insertCategory(category, callbackAddingCategory)
    }

    fun convertProductsToProductsRoom(productList: ProductList): ArrayList<ProductRoom> {
        val listofProductRoom = ArrayList<ProductRoom>()
        for (item: Product in productList.items) {
            listofProductRoom.add(ProductRoom(item))
        }
        return listofProductRoom
    }

    override fun onCleared() {
        super.onCleared()
    }

}