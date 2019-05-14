package com.example.magentotest.Room.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "category"
)
class CategoryRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "category_id")
    val categoryId: String,
    val position: Int,
    @ColumnInfo(name = "product_sku")
    var productSku: String
)