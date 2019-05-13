package com.example.magentotest.data.Product

data class Product(
    var attribute_set_id: Int=1,
    var created_at: String="",
    var custom_attributes: List<CustomAttribute> = listOf(),
    var extension_attributes: ExtensionAttributes?=null,
    var id: Int=1,
    var media_gallery_entries: List<MediaGalleryEntry> = listOf(),
    var name: String="",
    var options: List<Any> = listOf(),
    var price: Double=1.0,
    var product_links: List<Any> = listOf(),
    var sku: String="",
    var status: Int=1,
    var tier_prices: List<Any> = listOf(),
    var type_id: String="",
    var updated_at: String="",
    var visibility: Int=-1,
    var weight: Int=1
)