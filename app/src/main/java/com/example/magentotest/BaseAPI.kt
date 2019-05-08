package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import com.example.magentotest.data.ProductForAdding.ProductForAdding

interface BaseAPI {

    fun updateProduct(
        viewModel: MutableLiveData<Boolean>,
        sku:String,
        product: ProductForAdding, selectedImage: String)

    fun getAllProduct(viewModel: ProductViewModel)

    fun insertImage(viewModel: ProductViewModel)

    fun insertProduct(product: ProductForAdding, selectedImage: String)


}