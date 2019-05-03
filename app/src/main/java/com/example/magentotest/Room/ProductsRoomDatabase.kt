package com.example.magentotest

import android.arch.persistence.room.*
import android.content.Context
import com.example.magentotest.Room.Model.ProductRoom

@Database(entities = arrayOf(ProductRoom::class), version = 1)
abstract class ProductsRoomDatabase : RoomDatabase() {

    abstract fun userDao(): ProductDAO

    companion object {
        private var INSTANCE: ProductsRoomDatabase? = null

        fun getInstance(context: Context): ProductsRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductsRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ProductsRoomDatabase::class.java, "MagentoDataBase.db")
                        //  .allowMainThreadQueries()         // в основном потоке
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}