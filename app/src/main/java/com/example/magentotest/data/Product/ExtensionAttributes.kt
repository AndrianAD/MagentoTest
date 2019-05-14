package com.example.magentotest.data.Product

data class ExtensionAttributes(
    val category_links: List<CategoryLink>? = null,
    val stock_item: StockItem? = null,
    val website_ids: List<Int>? = null
)