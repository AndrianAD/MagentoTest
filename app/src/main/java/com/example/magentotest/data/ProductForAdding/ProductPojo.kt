package com.example.magentotest.data.ProductForAdding

data class ProductPojo(
    val product: ProductForAdding
)

data class ProductForAdding(
    val sku: String,
    val name: String,
    val price: Int,
    val status: Int = 1,
    val type_id: String = "simple",
    val attribute_set_id: Int = 4,
    val weight: Int
)