package com.example.magentotest

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.magentotest.data.ProductForAdding.ProductForAdding

interface BaseAPI {



    fun getAllProduct(viewModel: ProductViewModel)

    fun insertImage(viewModel: ProductViewModel)

    fun insertProduct(product: ProductForAdding, selectedImage: String)


}