package com.example.magentotest.Room.Model

import android.arch.persistence.room.*
import com.example.magentotest.Model.Product.Product

@Entity(tableName = "products", indices = arrayOf(Index(value = ["sku"], unique = true)))

data class ProductRoom(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,

    @ColumnInfo(name = "type_id")
    var typeId: String,
    var price: Double,
    var sku: String,
    var status: Int = 1,
    var quantity: Int = 0
    ) {


    //TODO quantity
    @Ignore
    constructor(product: Product) : this(
        0, product.name, product.type_id, product.price, product.sku, product.status,
        0)
}