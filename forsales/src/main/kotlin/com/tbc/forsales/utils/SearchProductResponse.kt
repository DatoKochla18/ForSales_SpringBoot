package com.tbc.forsales.utils

data class SearchProductResponse(
    val productId: Long,
    val productName: String,
    val productImgUrl: String,
    val company: List<SearchCompanyDto>,
    val productCategory: String,
)