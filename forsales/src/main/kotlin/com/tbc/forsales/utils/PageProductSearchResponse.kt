package com.tbc.forsales.utils

data class PageProductSearchResponse(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val totalElements: Long,
    val data: List<SearchProductResponse>
)