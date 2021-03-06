package com.example.magentotest.Model.Product

data class Product(
    val attribute_set_id: Int,
    val created_at: String,
    val custom_attributes: List<CustomAttribute>,
    val extension_attributes: ExtensionAttributes,
    val id: Int,
    val media_gallery_entries: List<MediaGalleryEntry>,
    val name: String,
    val options: List<Any>,
    val price: Double,
    val product_links: List<Any>,
    val sku: String,
    val status: Int,
    val tier_prices: List<Any>,
    val type_id: String,
    val updated_at: String,
    val visibility: Int,
    val weight: Int
)