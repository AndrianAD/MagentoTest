package com.example.magentotest

import android.arch.persistence.room.*
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.Room.Model.ProductWithImages

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageRoom)

    @Delete
    fun delete(product: ProductRoom)

    @Query("SELECT * FROM products")
    fun getAll(): List<ProductRoom>

    @Query("SELECT * FROM images WHERE product_sku = :sku")
    fun getBySKU(sku: String): ImageRoom

    @Query("Select * FROM products")
    fun loadProductWithImages(): List<ProductWithImages>

    @Query("DELETE FROM products")
    fun delleteAllProducts()

    @Query("DELETE FROM IMAGES")
    fun delleteAllImages()

}
