package com.tbc.forsales.controller

import com.tbc.forsales.model.Product
import com.tbc.forsales.service.ProductService
import com.tbc.forsales.utils.PagedProductResponse
import com.tbc.forsales.utils.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/lowest-priced")
    fun getLowestPricedProducts(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "6") perPage: Int
    ): PagedProductResponse {
        return productService.getLowestPricedProducts(page, perPage)
    }

    @GetMapping("/{productId}")
    fun getProductsByProductId(@PathVariable productId: Int): ResponseEntity<List<Product>> {
        val products = productService.getProductsByProductId(productId)
        return if (products.isNotEmpty()) {
            ResponseEntity.ok(products)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}

