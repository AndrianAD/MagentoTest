package com.example.magentotest.data.Product

data class Product(
    var attribute_set_id: Int,
    var created_at: String,
    var custom_attributes: List<CustomAttribute>,
    var extension_attributes: ExtensionAttributes,
    var id: Int,
    var media_gallery_entries: List<MediaGalleryEntry>,
    var name: String,
    var options: List<Any>,
    var price: Int,
    var product_links: List<Any>,
    var sku: String,
    var status: Int,
    var tier_prices: List<Any>,
    var type_id: String,
    var updated_at: String,
    var visibility: Int,
    var weight: Int
)