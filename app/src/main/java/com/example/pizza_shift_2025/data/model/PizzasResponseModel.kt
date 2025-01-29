package com.example.pizza_shift_2025.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzasResponseModel(
    val success: Boolean,
    val reason: String? = null,
    val catalog: List<PizzaModel>
)