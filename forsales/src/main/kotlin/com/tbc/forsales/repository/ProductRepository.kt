package com.tbc.forsales.repository

import com.tbc.forsales.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product, Long> {

    @Query(
        """
        SELECT p FROM Product p WHERE p.productPrice = (
            SELECT MIN(p2.productPrice) FROM Product p2 WHERE p2.productId = p.productId
        )
        AND (:category IS NULL OR p.productCategory = :category)
        AND (:search IS NULL OR p.productName LIKE CONCAT('%', :search, '%'))
        """
    )
    fun findLowestPricedProducts(
        @Param("category") category: String?,
        @Param("search") search: String?,
        pageable: Pageable
    ): Page<Product>
    fun findByProductId(productId: Int): List<Product>

    @Query("SELECT p FROM Product p WHERE p.productId IN :productIds")
    fun findByProductIds(@Param("productIds") productIds: List<Long>): List<Product>

}
