package com.example.magentotest.Model.Product

data class ExtensionAttributes(
    var category_links: List<CategoryLink>? = null,
    val stock_item: StockItem? = null,
    val website_ids: List<Int>? = null
)