package com.tbc.forsales.model

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val title: String,
    @Column(name = "category_img_url")
    val categoryImgUrl: String?
)