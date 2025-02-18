package com.tbc.forsales.utils

data class PagedProductResponse(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val data: List<ProductResponse>
)
