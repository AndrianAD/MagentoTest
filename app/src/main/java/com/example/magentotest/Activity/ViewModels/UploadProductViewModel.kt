package com.example.magentotest.Activity.ViewModels

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.App
import com.example.magentotest.Model.CategoryPojo
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Model.ProductForAdding.ProductForAdding
import com.example.magentotest.ProductDAO
import com.example.magentotest.ProductsRoomDatabase
import com.example.magentotest.RetrofitAPI

class UploadProductViewModel(application: Application) : BaseViewModel(application) {


    private var retrofitAPI: RetrofitAPI = RetrofitAPI()
    var callbackUpdateLivedata = MutableLiveData<Boolean>()
    var callbackInsertLivedata = MutableLiveData<Boolean>()
    var allCategories: MutableLiveData<CategoryPojo> = MutableLiveData()
    var productsList = MutableLiveData<ProductList>()

    private var productDB: ProductsRoomDatabase
    var productDao: ProductDAO

    init {
        productDB = App.DataBASE
        productDao = productDB.userDao()
    }

    fun insertProduct(livedata: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String?) {
        retrofitAPI.insertProduct(livedata, product, selectedImage)
    }

    fun updateProduct(livedata: MutableLiveData<Boolean>, sku: String, product: ProductForAdding, selectedImage: String?
    ) {
        retrofitAPI.updateProduct(livedata, sku, product, selectedImage)
    }

    fun getAllCategories() {
        retrofitAPI.getAllCategories(allCategories)
    }

    fun getAllProducts() {
        retrofitAPI.getAllProductsFromServer(productsList)
    }

    override fun onCleared() {
        super.onCleared()
    }
}
