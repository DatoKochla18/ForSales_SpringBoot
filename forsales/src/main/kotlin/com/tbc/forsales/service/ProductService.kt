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

    fun getLowestPricedProducts(page: Int, perPage: Int, search: String?, category: String?): PagedProductResponse {
        val pageable = PageRequest.of(page - 1, perPage)
        val productPage = productRepository.findLowestPricedProducts(category, search, pageable)

        logger.info("Found ${productPage.totalElements} products with filters: search=$search, category=$category")

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

    fun getFavouriteProducts(productIds: List<Long>): List<FavouriteProduct> {
        val products = productRepository.findByProductIds(productIds)

        return products
            .groupBy { it.productId }  // Ensure productId is Long
            .map { (productId, productList) ->
                val minPrice = productList.minOf { it.productPrice }  // Find lowest price
                val companies = productList
                    .filter { it.productPrice == minPrice }
                    .joinToString(", ") { it.company }  // Get company names

                val product = productList.first()  // Get details from any product object

                FavouriteProduct(
                    productId = productId,
                    productName = product.productName,
                    productImgUrl = product.productImgUrl,
                    company = companies,
                    productPrice = minPrice
                )
            }
    }


}

