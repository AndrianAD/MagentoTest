package com.example.magentotest.data.CategorieForAdding

data class Category(
    val isActive: Boolean=true,
    val level: Int,
    val name: String,
    val parent_id: Int
)