package com.example.magentotest.Model

data class CategoryPojo(
    var children_data: List<CategoryPojo>,
    var id: Int,
    var is_active: Boolean,
    var level: Int,
    var name: String,
    var parent_id: Int,
    var position: Int,
    var product_count: Int
)