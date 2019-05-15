package com.example.magentotest

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.magentotest.Activity.ViewModels.UserViewModel
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.CategorieForAdding.CategorieForAdding
import com.example.magentotest.data.CategoryPojo
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList
import com.example.magentotest.data.ProductForAdding.ProductForAdding

class RoomAPI : BaseAPI {


    var dataBase = App.DataBASE
    var productDao = dataBase!!.userDao()


    override fun getProductbySKU(sku: String, callback: MutableLiveData<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateProduct(
        viewModel: MutableLiveData<Boolean>,
        sku: String,
        product: ProductForAdding,
        selectedImage: String?
    ) {

    }

    override fun getCategoryById(id: Int, livedata: MutableLiveData<CategoryPojo>) {

    }

    override fun getAllCategories(livedata: MutableLiveData<CategoryPojo>) {

    }


    override fun insertProduct(viewModel: MutableLiveData<Boolean>, product: ProductForAdding, selectedImage: String?) {

    }

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


    override fun insertCategory(category: CategorieForAdding, addingCategory: MutableLiveData<Boolean>) {

//        var productList = productsList
//        if (productList.items.isNotEmpty()) {
//            val listOfFoto: ArrayList<CategoryRoom> = ArrayList()
//            for (product in productList.items) {
//                if (product.extension_attributes.category_links!!.isNotEmpty()) {
//                    for (item in product.extension_attributes.category_links)
//                        listOfFoto.add(CategoryRoom(0, item.category_id,position = 0,productSku = product.sku ))
//                    Log.i("Magento", "insertImagesToDB" + "thread: " + Thread.currentThread().name)
//                }
//            }
//            for (item in listOfFoto) {
//                productDao.insertImageToDB(item)
//            }
//        }
    }

    fun getAllProductFromRoom(viewModel: UserViewModel) {
        App.productWithImageAndCategories = productDao.loadProductWithImages()
        viewModel.productWithImage.postValue(true)
    }


}