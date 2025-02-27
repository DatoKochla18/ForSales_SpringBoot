package com.tbc.forsales.controller

import com.tbc.forsales.model.Category
import com.tbc.forsales.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getCategories(): List<Category> = categoryService.getAllCategories()
}