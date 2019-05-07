package com.example.magentotest.data.ImageForAdding

data class Content(
    val base64EncodedData: String,
    val name: String="choose_any_name",
    val type: String="image/png"
)