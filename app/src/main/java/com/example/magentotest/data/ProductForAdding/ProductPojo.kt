package com.example.magentotest.data.ProductForAdding

import com.example.magentotest.data.Product.ExtensionAttributes

data class ProductPojo(
    val product: ProductForAdding
)

data class ProductForAdding(
    val sku: String,
    val name: String,
    val price: Double,
    val status: Int = 1,
    val type_id: String = "simple",
    val attribute_set_id: Int = 4,
    val weight: Int,
    val extension_attributes:ExtensionAttributes?=null
)