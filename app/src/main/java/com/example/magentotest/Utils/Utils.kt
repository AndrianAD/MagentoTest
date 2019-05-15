package com.example.magentotest.Utils

import com.example.magentotest.Model.Product.Product
import com.example.magentotest.Model.Product.ProductList
import com.example.magentotest.Room.Model.ProductRoom

object Utils {

    fun convertProductsToProductsRoom(productList: ProductList): ArrayList<ProductRoom> {
        val listofProductRoom = ArrayList<ProductRoom>()
        for (item: Product in productList.items) {
            listofProductRoom.add(ProductRoom(item))
        }
        return listofProductRoom
    }

}