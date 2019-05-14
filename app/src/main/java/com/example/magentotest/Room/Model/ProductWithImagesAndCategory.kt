package com.example.magentotest.Room.Model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class ProductWithImagesAndCategory(
    @Embedded
    var productRoom: ProductRoom = ProductRoom(-1, "", "", -1.0, "", -1, -1),

    @Relation(
        parentColumn = "sku",
        entityColumn = "product_sku"
    )
    var images: List<ImageRoom> = listOf(),

    @Relation(
        parentColumn = "sku",
        entityColumn = "product_sku"
    )
    var categories: List<CategoryRoom> = listOf()


)
