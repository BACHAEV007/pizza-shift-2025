package com.example.pizza_shift_2025.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Size(
    val name: SizeType,
    val price: Int
)