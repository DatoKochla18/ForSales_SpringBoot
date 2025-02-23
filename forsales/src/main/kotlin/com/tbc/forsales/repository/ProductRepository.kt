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
    """
    )
    fun findLowestPricedProducts(pageable: Pageable): Page<Product>

    fun findByProductId(productId: Int): List<Product>

    @Query(
        """
    SELECT p FROM Product p 
    WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))
    """
    )
    fun searchProductsByName(@Param("name") name: String, pageable: Pageable): Page<Product>

}
