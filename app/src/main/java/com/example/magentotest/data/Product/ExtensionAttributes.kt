package com.example.magentotest.data.Product

data class ExtensionAttributes(
    val stock_item: StockItem,
    val website_ids: List<Int> = listOf()
)