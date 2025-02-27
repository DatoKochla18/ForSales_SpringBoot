package com.tbc.forsales.repository

import com.tbc.forsales.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductCartRepository : JpaRepository<Product, Int> {

    fun findByCompanyAndProductId(company: String, productId: Long): Product?

    @Query("select distinct p.company from Product p")
    fun findDistinctCompanies(): List<String>

    // Returns any product with the given productId (the reference product)
    fun findFirstByProductId(productId: Long): Product?

    // Returns a sample product for the given company so we can use its company info if needed
    fun findFirstByCompany(company: String): Product?
}