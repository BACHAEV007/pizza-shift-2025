package com.example.pizza_shift_2025.data.model

import com.example.pizza_shift_2025.domain.entity.IngredientType
import kotlinx.serialization.Serializable

@Serializable
data class IngredientModel(
    val name: IngredientType,
    val cost: Int,
    val img: String

)