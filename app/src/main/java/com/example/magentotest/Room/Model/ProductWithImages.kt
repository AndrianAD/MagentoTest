package com.example.magentotest.Room.Model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class ProductWithImages(
    @Embedded
    var productRoom: ProductRoom=ProductRoom(-1,"","",-1,"",-1,-1),

    @Relation(
        parentColumn = "sku",
        entityColumn = "product_sku")
    var images: List<ImageRoom> = listOf()
)
