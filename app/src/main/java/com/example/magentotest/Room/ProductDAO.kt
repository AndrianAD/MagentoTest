package com.example.magentotest

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.magentotest.Room.Model.CategoryRoom
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom
import com.example.magentotest.Room.Model.ProductWithImagesAndCategory

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageRoom)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: CategoryRoom)

    @Query("SELECT * FROM products")
    fun getAllProductRoom(): List<ProductRoom>

    @Query("SELECT * FROM images WHERE product_sku = :sku")
    fun getImagesBySKU(sku: String): ImageRoom

    @Query("Select * FROM products WHERE sku = :sku")
    fun getProductWithImagesbySKU(sku: String): ProductWithImagesAndCategory

    @Query("Select * FROM products")
    fun loadProductWithImages(): List<ProductWithImagesAndCategory>

    @Query("DELETE FROM products")
    fun deleteAllProducts()

    @Query("DELETE FROM IMAGES")
    fun deleteAllImages()

}
