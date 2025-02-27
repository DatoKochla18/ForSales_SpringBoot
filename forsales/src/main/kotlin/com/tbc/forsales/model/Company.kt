package com.tbc.forsales.model

import jakarta.persistence.*

@Entity
@Table(name = "companies")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val company: String,
    @Column(name = "company_img_url")
    val companyImgUrl: String?
)