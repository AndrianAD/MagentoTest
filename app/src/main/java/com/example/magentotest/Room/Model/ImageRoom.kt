package com.example.magentotest.Room.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "images",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ProductRoom::class,
            parentColumns = arrayOf("sku"),
            childColumns = arrayOf("product_sku"),
            onDelete = CASCADE
        )
    )
)
data class ImageRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo(name = "path_image")
    var pathImage: String,
    @ColumnInfo(name = "product_sku")
    var productSku: String
)
