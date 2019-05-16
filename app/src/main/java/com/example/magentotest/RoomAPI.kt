package com.example.magentotest

import android.util.Log
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Room.Model.CategoryRoom
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom

class RoomAPI {


    var dataBase = App.DataBASE
    var productDao = dataBase!!.userDao()

    fun insertProductRoomList(list: List<ProductRoom>) {
        productDao.insertProductRoomList(list)

    }

    fun insertImage(productList: ProductList) {
        if (productList.items.isNotEmpty()) {
            val listOfFoto: ArrayList<ImageRoom> = ArrayList()
            for (product in productList.items) {
                if (product.media_gallery_entries.isNotEmpty()) {
                    for (item in product.media_gallery_entries)
                        listOfFoto.add(ImageRoom(0, item.file, product.sku))
                    Log.i("Magento", "insertImagesToDB" + "thread: " + Thread.currentThread().name)
                }
            }
            productDao.insertListOfImage(listOfFoto)
        }
    }

    fun insertCategory(productList: ProductList) {
        if (productList.items.isNotEmpty()) {
            val listOfCategoryRoom: ArrayList<CategoryRoom> = ArrayList()
            for (product in productList.items) {
                product.extension_attributes.category_links?.let {
                    if (it.size>0) {
                        for (item in product.extension_attributes.category_links!!)
                            listOfCategoryRoom.add(CategoryRoom(0, item.category_id,position = 0,productSku = product.sku ))
                        Log.i("Magento", "insertCategoryToDB" + "thread: " + Thread.currentThread().name)
                    }
                }

            }
            for (item in listOfCategoryRoom) {
                productDao.insertCategory(item)
            }
        }
    }

    fun getAllProductFromRoom(viewModel: UserViewModel) {
        App.productWithImageAndCategories = productDao.loadProductWithImages()
        viewModel.productWithImage.postValue(true)
    }


}