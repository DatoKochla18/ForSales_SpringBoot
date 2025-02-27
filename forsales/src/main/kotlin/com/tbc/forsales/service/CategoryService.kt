package com.tbc.forsales.service

import com.tbc.forsales.model.Category
import com.tbc.forsales.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    fun getAllCategories(): List<Category> = categoryRepository.findAll()
}