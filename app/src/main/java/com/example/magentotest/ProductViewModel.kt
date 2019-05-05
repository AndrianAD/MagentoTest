package com.example.magentotest

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {


    private val TAG = javaClass.simpleName
    var tokenLIVE = MutableLiveData<String>()
    lateinit var productsList: ProductList
    var productsRoomLIVE = MutableLiveData<List<ProductRoom>>()
    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()

    var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = ProductsRoomDatabase.getInstance(application)!!
        productDao = productDB.userDao()
    }


    fun gelAllProduct(type: String) {
        android.util.Log.e(
            "Magento", "getAllProduct, type: "
                    + type + ", thread: " + Thread.currentThread().name
        )
        when (type) {
            "Retrofit" -> retrofitAPI.getAllProduct(this)
            "Room" -> roomAPI.getAllProduct(this)
        }
    }


    fun insert(productRoom: ProductRoom) {
        productDao.insert(productRoom)
    }

    fun saveToDb() {
        Log.e("Magento", "saveToDB, " + ", thread: " + Thread.currentThread().name)
        var listofProductRoom = convertProductsToProductsRoom(productsList)
        saveImages(productsList)
        GlobalScope.launch(Dispatchers.Default) {
            for (item in listofProductRoom) {
                insert(item)
            }
            gelAllProduct("Room")
        }
    }

    fun saveImages(products: ProductList) {

        if (products.items.isNotEmpty()) {
            val listOfFoto: ArrayList<ImageRoom> = ArrayList()
            for (product in products.items) {
                if (product.media_gallery_entries.isNotEmpty()) {
                    for (item in product.media_gallery_entries)
                        listOfFoto.add(ImageRoom(0, item.file, product.sku))
                    Log.e("Magento", "saveImages" + "thread: " + Thread.currentThread().name)
                }
            }

            GlobalScope.launch(Dispatchers.Default) {
                for (item in listOfFoto) {
                    productDao.insertImage(item)
                }
            }
        }

    }


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