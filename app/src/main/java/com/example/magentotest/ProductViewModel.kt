package com.example.magentotest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Retrofit.RetrofitFactory
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    var tokenLIVE = MutableLiveData<String>()
    lateinit var productsList: ProductList
    var productsRoomLIVE = MutableLiveData<List<ProductRoom>>()
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()

    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO
    val retrofit = RetrofitFactory.retrofitInstance

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }


    fun getAllProduct(type: String) {
        Log.e("Magento", "getAllProduct, type: "
                    + type + ", thread: " + Thread.currentThread().name)
        when (type) {
            "Retrofit" -> retrofitAPI.getAllProduct(this)
            "Room" -> roomAPI.getAllProduct(this)
        }
    }


    fun insertProductToDB(productRoom: ProductRoom) {
        productDao.insert(productRoom)
    }

    fun saveProductToDb() {
        Log.e("Magento", "saveToDB, " + ", thread: " + Thread.currentThread().name)
        var listofProductRoom = convertProductsToProductsRoom(productsList)
        roomAPI.insertImage(this)
        GlobalScope.launch(Dispatchers.Default) {
            for (item in listofProductRoom) {
                insertProductToDB(item)
            }
            getAllProduct("Room")
        }
    }

//    fun insertImagesToDB(products: ProductList) {
//
//        if (products.items.isNotEmpty()) {
//            val listOfFoto: ArrayList<ImageRoom> = ArrayList()
//            for (product in products.items) {
//                if (product.media_gallery_entries.isNotEmpty()) {
//                    for (item in product.media_gallery_entries)
//                        listOfFoto.add(ImageRoom(0, item.file, product.sku))
//                    Log.e("Magento", "insertImagesToDB" + "thread: " + Thread.currentThread().name)
//                }
//            }
//            GlobalScope.launch(Dispatchers.Default) {
//                for (item in listOfFoto) {
//                    productDao.insertImage(item)
//                }
//            }
//        }
//    }


    fun convertProductsToProductsRoom(productList: ProductList): ArrayList<ProductRoom> {
        var listofProductRoom = ArrayList<ProductRoom>()
        for (item: Product in productList.items) {
            listofProductRoom.add(ProductRoom(item))
        }
        return listofProductRoom
    }

    override fun onCleared() {
        super.onCleared()
    }



}