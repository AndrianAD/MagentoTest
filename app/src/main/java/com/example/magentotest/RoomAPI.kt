package com.example.magentotest

import android.util.Log
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.data.ProductForAdding.ProductForAdding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RoomAPI : BaseAPI {
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
                    Log.e("Magento", "insertImagesToDB" + "thread: " + Thread.currentThread().name)
                }
            }
            GlobalScope.launch(Dispatchers.Default) {
                for (item in listOfFoto) {
                    viewModel.productDao.insertImage(item)
                }
            }
        }
    }


    override fun getAllProduct(viewModel: ProductViewModel) {

        viewModel.productsRoomLIVE.postValue(viewModel.productDao.getAll())

    }


}