package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.App
import com.example.magentotest.Model.CategoryPojo
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.Room.Model.CategoryRoom

class DetailsActivityViewModel(application: Application) : BaseViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var productDB: ProductsRoomDatabase
    var callbackGetCategoryById: MutableLiveData<CategoryPojo> = MutableLiveData()
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }

   fun getCategoriesByProductSku(productSku:String): List<CategoryRoom> {
       return productDao.getCategoriesBySKU(productSku)
   }

    fun getCategoriesByID(id : Int){
       retrofitAPI.getCategoryById(id,callbackGetCategoryById)
    }



    override fun onCleared() {
        super.onCleared()
    }
}