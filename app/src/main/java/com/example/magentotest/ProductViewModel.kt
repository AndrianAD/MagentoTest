package com.example.magentotest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import java.util.ArrayList

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val TAG = javaClass.simpleName
    var tokenLIVE = MutableLiveData<String>()
    var productsListLIVE = MutableLiveData<ProductList>()
    var productsRoomLIVE = MutableLiveData<List<ProductRoom>>()
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()

    var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }


    fun gelAllProduct(baseAPI: BaseAPI) {
        if (baseAPI is RetrofitAPI)
            retrofitAPI.getAllProduct(this)

        if (baseAPI is RoomAPI)
            roomAPI.getAllProduct(this)
    }


    fun insert(productRoom: ProductRoom){
        productDao.insert(productRoom)
    }


    fun convertProductsToProductsRoom(productList:ProductList): ArrayList<ProductRoom> {
        var listofProductRoom = ArrayList<ProductRoom>()
        for (item: Product in productList.items) {
            listofProductRoom.add(ProductRoom(item, ))
        }
        return listofProductRoom
    }



    override fun onCleared() {
        super.onCleared()
    }
}