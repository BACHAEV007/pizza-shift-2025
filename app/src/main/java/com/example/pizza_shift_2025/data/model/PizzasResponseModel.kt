package com.example.pizza_shift_2025.data.model


data class PizzasResponseModel(
    val success: Boolean,
    val reason: String?,
    val catalog: List<PizzaModel>
)