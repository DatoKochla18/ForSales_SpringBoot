package com.tbc.forsales.service


import com.tbc.forsales.model.Company
import com.tbc.forsales.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {
    fun getAllCategories(): List<Company> = companyRepository.findAll()
}