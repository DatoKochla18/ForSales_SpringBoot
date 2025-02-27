package com.tbc.forsales.controller

import com.tbc.forsales.model.Product
import com.tbc.forsales.service.ProductService
import com.tbc.forsales.utils.FavouriteProduct
import com.tbc.forsales.utils.PagedProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping("/lowest_priced")
    fun getLowestPricedProducts(
        @RequestParam(defaultValue = "1") page: Int,
        @RequestParam(defaultValue = "20") perPage: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) category: String?
    ): PagedProductResponse {
        return productService.getLowestPricedProducts(page, perPage, search, category)
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


    @GetMapping("/favourite")
    fun getFavouriteProducts(@RequestParam("ids") ids: String): List<FavouriteProduct> {
        val productIds = ids.replace("%2C", ",").split(",").map { it.trim().toLong() }
        return productService.getFavouriteProducts(productIds)
    }

}

