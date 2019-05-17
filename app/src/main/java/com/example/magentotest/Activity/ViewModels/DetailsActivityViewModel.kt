package com.example.magentotest.Activity.ViewModels


import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.App
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI
import com.example.magentotest.Room.Model.ProductWithImagesAndCategory

class DetailsActivityViewModel(application: Application) : BaseViewModel(application) {

    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    private var productDB: ProductsRoomDatabase
    var productWithImagesAndCategory = MutableLiveData<ProductWithImagesAndCategory>()
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }


    fun getProductWithImagesbySKU(sku: String) {
        productWithImagesAndCategory.postValue(productDao.getProductWithImagesbySKU(sku))


    }

    override fun onCleared() {
        super.onCleared()
    }
}