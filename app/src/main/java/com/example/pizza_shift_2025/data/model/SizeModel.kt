package com.example.pizza_shift_2025.data.model
import kotlinx.serialization.Serializable

@Serializable
data class SizeModel(
    val name: SizeTypeModel,
    val price: Int
)