package com.tbc.forsales.repository

import com.tbc.forsales.model.Category
import com.tbc.forsales.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long>