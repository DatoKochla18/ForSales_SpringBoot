package com.tbc.forsales.controller

import com.tbc.forsales.model.Company
import com.tbc.forsales.service.CompanyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/companies")
class CompanyController(private val companyService: CompanyService) {

    @GetMapping
    fun getCategories(): List<Company> = companyService.getAllCategories()
}