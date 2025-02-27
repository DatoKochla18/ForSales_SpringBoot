package com.tbc.forsales.utils

data class ProductCartResponse(
    val productId: Long,
    val productName: String,
    val productImgUrl: String,
    val company: String,
    val productPrice: Double?  // nullable so that missing products can have null price
)