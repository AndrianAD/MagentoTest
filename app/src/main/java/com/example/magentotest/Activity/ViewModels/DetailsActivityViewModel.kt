package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.*
import com.example.magentotest.Model.Product.Product
import com.example.magentotest.Room.Model.CategoryRoom

class DetailsActivityViewModel(application: Application) : BaseViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var roomAPI: RoomAPI = RoomAPI()
    private var productDB: ProductsRoomDatabase
    var callbackGetProductbySKU = MutableLiveData<Product>()
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }

   fun getCategoriesByProductSku(productSku:String): List<CategoryRoom> {
       return productDao.getCategoriesBySKU(productSku)
   }

    override fun onCleared() {
        super.onCleared()
    }
}