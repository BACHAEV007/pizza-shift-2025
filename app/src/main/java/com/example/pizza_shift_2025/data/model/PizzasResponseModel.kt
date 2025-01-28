package com.example.pizza_shift_2025.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PizzasResponseModel(
    val pizzaModel: List<PizzaModel>,
    val reason: String,
    val success: Boolean
)