package com.example.magentotest.Model.ImageForAdding

data class Entry(
    val content: Content,
    val disabled: Boolean=false,
    val label: String="Image",
    val media_type: String="image",
    val position: Int=1,
    val types: List<String> = listOf("image", "small_image", "thumbnail")
)