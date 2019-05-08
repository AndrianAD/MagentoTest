package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class RoomAPI : BaseAPI {
    override fun updateProduct(viewModel: MutableLiveData<Boolean>, sku: String, product: ProductForAdding, selectedImage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertProduct(product: ProductForAdding, selectedImage: String) {

    }

    override fun insertImage(viewModel: ProductViewModel) {

        var productList = viewModel.productsList
        if (productList.items.isNotEmpty()) {
            val listOfFoto: ArrayList<ImageRoom> = ArrayList()
            for (product in productList.items) {
                if (product.media_gallery_entries.isNotEmpty()) {
                    for (item in product.media_gallery_entries)
                        listOfFoto.add(ImageRoom(0, item.file, product.sku))
                    Log.i("Magento", "insertImagesToDB" + "thread: " + Thread.currentThread().name)
                }
            }
            for (item in listOfFoto) {
                viewModel.productDao.insertImage(item)
            }
        }
    }


    override fun getAllProduct(viewModel: ProductViewModel) {
        App.productWithImages = viewModel.productDao.loadProductWithImages()
        viewModel.productWithImage.postValue(true)
    }


}