package com.example.pizza_shift_2025.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientModel(
    val name: IngredientTypeModel,
    val cost: Int,
    val img: String

)