package com.example.magentotest.data.Product

data class ExtensionAttributes(
    val category_links: List<CategoryLink>,
    val stock_item: StockItem,
    val website_ids: List<Int>
)