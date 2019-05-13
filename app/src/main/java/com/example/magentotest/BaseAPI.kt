package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.CategoryPojo
import com.example.magentotest.data.ProductForAdding.ProductForAdding

interface BaseAPI {

    fun updateProduct(viewModel: MutableLiveData<Boolean>, sku:String, product: ProductForAdding, selectedImage: String)

    fun getAllProduct(viewModel: UserViewModel)

    fun insertImage(viewModel: UserViewModel)

    fun insertProduct(viewModel: MutableLiveData<Boolean>,product: ProductForAdding, selectedImage: String)

    fun insertCategory(category: CategorieForAdding, addingCategory: MutableLiveData<Boolean>)
    fun getAllCategories(livedata: MutableLiveData<CategoryPojo>)

}