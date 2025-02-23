package com.tbc.forsales.service

import com.tbc.forsales.model.Product
import com.tbc.forsales.repository.ProductRepository
import com.tbc.forsales.utils.*
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(ProductService::class.java)
    }

    fun getLowestPricedProducts(page: Int, perPage: Int): PagedProductResponse {
        val pageable = PageRequest.of(page - 1, perPage) // PageRequest is 0-based
        val productPage = productRepository.findLowestPricedProducts(pageable)

        logger.info("Found ${productPage.totalElements} products")

        val groupedProducts = productPage.content.groupBy { it.productId }
            .map { (_, productList) ->
                val firstProduct = productList.first()

                ProductResponse(
                    productId = firstProduct.productId,
                    productName = firstProduct.productName,
                    productImgUrl = firstProduct.productImgUrl,
                    company = productList.map { product ->
                        CompanyDto(name = product.company, companyImgUrl = product.companyImgUrl)
                    },
                    productCategory = firstProduct.productCategory,
                    productPrice = firstProduct.productPrice
                )
            }

        return PagedProductResponse(
            page = page,
            perPage = perPage,
            totalPages = productPage.totalPages,
            totalElements = productPage.totalElements,
            data = groupedProducts
        )
    }

    fun getProductsByProductId(productId: Int): List<Product> {
        return productRepository.findByProductId(productId)
    }

    fun searchProducts(query: String, page: Int, perPage: Int): PageProductSearchResponse {
        val pageable = PageRequest.of(page - 1, perPage)
        val productPage = productRepository.searchProductsByName(query, pageable)

        logger.info("Found ${productPage.totalElements} products for query: $query")

        val groupedProducts = productPage.content.groupBy { it.productId }
            .map { (_, productList) ->
                val firstProduct = productList.first()

                SearchProductResponse(
                    productId = firstProduct.productId,
                    productName = firstProduct.productName,
                    productImgUrl = firstProduct.productImgUrl,
                    company = productList.map { product ->
                        SearchCompanyDto(
                            name = product.company,
                            companyImgUrl = product.companyImgUrl,
                            productPrice = product.productPrice
                        )
                    },
                    productCategory = firstProduct.productCategory
                )
            }

        return PageProductSearchResponse(
            page = page,
            perPage = perPage,
            totalPages = productPage.totalPages,
            totalElements = productPage.totalElements,
            data = groupedProducts
        )
    }


}

