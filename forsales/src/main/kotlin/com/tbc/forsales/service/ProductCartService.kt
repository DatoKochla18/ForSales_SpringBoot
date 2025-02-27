package com.tbc.forsales.service

import com.tbc.forsales.model.Product
import com.tbc.forsales.repository.ProductCartRepository
import com.tbc.forsales.utils.ProductCartResponse
import org.springframework.stereotype.Service


@Service
class ProductCartService(private val productCartRepository: ProductCartRepository) {

    fun compareProducts(ids: String, companyFilter: String? = null): MutableList<ProductCartResponse> {
        // Parse the comma-separated product ids into Long values.
        val productIds = ids.split(",")
            .mapNotNull { it.trim().toLongOrNull() }

        // If a company filter is provided, use it; otherwise, process all distinct companies.
        val companies = if (!companyFilter.isNullOrBlank()) {
            listOf(companyFilter)
        } else {
            productCartRepository.findDistinctCompanies()
        }
        val result = mutableListOf<ProductCartResponse>()

        companies.forEach { company ->
            // Get a sample product for the company (if needed for additional company details)
            val companySample = productCartRepository.findFirstByCompany(company)
            // For each provided product id, check if the product exists for this company.
            val productsForCompany = productIds.map { pid ->
                val product = productCartRepository.findByCompanyAndProductId(company, pid)
                if (product != null) {
                    // When product exists, use its details as-is.
                    product.toResponse()
                } else {
                    // If missing, use the master product details for productName and productImgUrl.
                    val masterProduct = productCartRepository.findFirstByProductId(pid)
                    ProductCartResponse(
                        productId = pid,
                        productName = masterProduct?.productName ?: "Unknown",
                        productImgUrl = masterProduct?.productImgUrl ?: "",
                        // Use the filtered company name
                        company = company,
                        productPrice = null // productPrice is null when the product doesn't exist for this company.
                    )
                }
            }
            result.addAll(productsForCompany)
        }
        return result
    }
}

fun Product.toResponse() = ProductCartResponse(
    productId = this.productId,
    productName = this.productName,
    productImgUrl = this.productImgUrl,
    productPrice = this.productPrice,
    company = this.company
)