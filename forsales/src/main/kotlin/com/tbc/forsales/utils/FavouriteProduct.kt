package com.tbc.forsales.utils

data class FavouriteProduct(
    val productId: Long,
    val productName: String,
    val productImgUrl: String,
    val company: String,
    val productPrice: Double
)