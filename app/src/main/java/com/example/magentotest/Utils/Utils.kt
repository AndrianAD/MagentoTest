package com.example.magentotest.Utils

import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.data.Product.Product
import com.example.magentotest.data.Product.ProductList

object Utils {

    fun convertProductsToProductsRoom(productList: ProductList): ArrayList<ProductRoom> {
        val listofProductRoom = ArrayList<ProductRoom>()
        for (item: Product in productList.items) {
            listofProductRoom.add(ProductRoom(item))
        }
        return listofProductRoom
    }

}