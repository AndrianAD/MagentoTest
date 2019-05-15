package com.example.magentotest.Model

data class CategoryPojo(
    val children_data: List<CategoryPojo>,
    val id: Int,
    val is_active: Boolean,
    val level: Int,
    val name: String,
    val parent_id: Int,
    val position: Int,
    val product_count: Int
)