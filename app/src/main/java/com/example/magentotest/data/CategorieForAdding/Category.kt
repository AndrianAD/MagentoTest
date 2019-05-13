package com.example.magentotest.data.CategorieForAdding

data class Category(
    var isActive: Boolean=true,
    var level: Int=1,
    var name: String="new Category",
    var parent_id: Int=2
)