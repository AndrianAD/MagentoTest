package com.example.magentotest.Room.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "Foo",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ProductRoom::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("product_id"),
            onDelete = CASCADE
        )
    )
)
data class ImageRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "path_image")
    var pathImages: List<String>,
    @ColumnInfo(name = "product_id")
    var productId: Int
)
