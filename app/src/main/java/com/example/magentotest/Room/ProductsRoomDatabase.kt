package com.example.magentotest

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.magentotest.Room.Model.ImageRoom
import com.example.magentotest.Room.Model.ProductRoom

@Database(entities = arrayOf(ProductRoom::class,ImageRoom::class), version = 1)
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