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

    @Query("SELECT * FROM products")
    fun getAllProductRoom(): List<ProductRoom>

    @Query("SELECT * FROM images WHERE product_sku = :sku")
    fun getImagesBySKU(sku: String): ImageRoom

    @Query("Select * FROM products WHERE sku = :sku")
    fun getProductWithImagesbySKU(sku: String): ProductWithImages

    @Query("Select * FROM products")
    fun loadProductWithImages(): List<ProductWithImages>

    @Query("DELETE FROM products")
    fun delleteAllProducts()

    @Query("DELETE FROM IMAGES")
    fun delleteAllImages()

}
