package com.tbc.forsales.controller

import com.tbc.forsales.service.ProductCartService
import com.tbc.forsales.utils.ProductCartResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/products")
class ProductCartController(private val productCartService: ProductCartService) {

    @GetMapping("/cart")
    fun compareProducts(
        @RequestParam ids: String,
        @RequestParam(required = false) company: String?
    ): ResponseEntity<MutableList<ProductCartResponse>> {
        val result = productCartService.compareProducts(ids, company)
        return ResponseEntity.ok(result)
    }
}