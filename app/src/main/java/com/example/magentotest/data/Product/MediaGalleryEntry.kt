package com.example.magentotest.data.Product

data class MediaGalleryEntry(
    val disabled: Boolean,
    val `file`: String,
    val id: Int,
    val label: String,
    val media_type: String,
    val position: Int,
    val types: List<String>
)