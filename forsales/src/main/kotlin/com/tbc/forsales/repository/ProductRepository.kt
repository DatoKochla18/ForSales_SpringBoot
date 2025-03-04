package com.tbc.forsales.repository

import com.tbc.forsales.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product, Long> {

    @Query(
        value = """
        SELECT * FROM tbc_products p 
        WHERE p.product_price = (
            SELECT MIN(p2.product_price) 
            FROM tbc_products p2 
            WHERE p2.product_id = p.product_id 
              AND (:category IS NULL OR p2.product_category = :category)
              AND (:search IS NULL OR p2.product_name LIKE CONCAT(N'%', :search, N'%'))
        )
    """,
        nativeQuery = true
    )
    fun findLowestPricedProducts(
        @Param("category") category: String?,
        @Param("search") search: String?,
        pageable: Pageable
    ): Page<Product>

    fun findByProductId(productId: Int): List<Product>

    @Query("SELECT p FROM Product p WHERE p.productId IN :productIds order by p.productPrice")
    fun findByProductIds(@Param("productIds") productIds: List<Long>): List<Product>

}
