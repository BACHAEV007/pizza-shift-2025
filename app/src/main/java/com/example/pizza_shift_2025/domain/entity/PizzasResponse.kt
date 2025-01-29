package com.example.pizza_shift_2025.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class PizzasResponse(
    val pizzaModel: List<Pizza>,
    val reason: String,
    val success: Boolean
)