package com.example.magentotest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.Product.ProductList
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class UploadProductViewModel(application: Application) : AndroidViewModel(application) {

    var tokenLIVE = MutableLiveData<String>()
    lateinit var productsList: ProductList
    var productsRoomLIVE = MutableLiveData<List<ProductRoom>>()
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()

    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }

    fun insertProduct(product: ProductForAdding, selectedImage: String) {
        retrofitAPI.insertProduct(product, selectedImage)
    }


    override fun onCleared() {
        super.onCleared()
    }
}
