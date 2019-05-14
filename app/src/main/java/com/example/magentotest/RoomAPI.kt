package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.CategoryPojo
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class RoomAPI : BaseAPI {

    var dataBase = App.DataBASE
    var productDao = dataBase!!.userDao()

    override fun getCategoryById(id: Int, livedata: MutableLiveData<CategoryPojo>) {

    }

    override fun getAllCategories(livedata: MutableLiveData<CategoryPojo>) {

    }

    override fun updateProduct(
        viewModel: MutableLiveData<Boolean>,
        sku: String,
        product: ProductForAdding,
        selectedImage: String
    ) {
    }

    override fun insertProduct(viewModel: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String) {


    }

    override fun insertImage(viewModel: UserViewModel) {

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
                productDao.insertImage(item)
            }
        }
    }


    override fun insertCategory(category: CategorieForAdding, addingCategory: MutableLiveData<Boolean>) {


    }


    override fun getAllProduct(viewModel: UserViewModel) {
        App.productWithImageAndCategories = productDao.loadProductWithImages()
        viewModel.productWithImage.postValue(true)
    }


}