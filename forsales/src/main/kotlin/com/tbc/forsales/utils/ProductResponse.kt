package com.tbc.forsales.utils

data class ProductResponse(
    val productId: Long,
    val productName: String,
    val productImgUrl: String,
    val company: List<CompanyDto>,
    val productCategory: String,
    val productPrice: Double
)