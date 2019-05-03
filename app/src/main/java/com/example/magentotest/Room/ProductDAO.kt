package com.example.magentotest

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.magentotest.Room.Model.ProductRoom

@Dao
interface ProductDAO {

    @Insert
    fun insert(product: ProductRoom)

    @Query("SELECT * FROM products")
    fun getAll(): List<ProductRoom>

    @Query("SELECT * FROM products WHERE sku = :sku")
    fun getBySKU(sku: String): ProductRoom

}
