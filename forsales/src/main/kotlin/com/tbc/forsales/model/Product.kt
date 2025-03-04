package com.tbc.forsales.model

import jakarta.persistence.*
import org.hibernate.annotations.Nationalized

@Entity
@Table(name = "tbc_products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(name = "product_id")
    val productId: Long,

    @Nationalized  // Ensure Unicode is supported
    @Column(name = "product_name", columnDefinition = "NVARCHAR(255)")
    val productName: String,

    @Column(name = "product_img_url")
    val productImgUrl: String,

    @Column(name = "company")
    val company: String,

    @Column(name = "company_img_url")
    val companyImgUrl: String,

    @Column(name = "product_category")
    val productCategory: String,

    @Column(name = "product_price")
    val productPrice: Double
)
