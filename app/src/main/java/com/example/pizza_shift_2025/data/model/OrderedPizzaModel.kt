package com.example.pizza_shift_2025.data.model

import com.example.pizza_shift_2025.data.dbentity.DoughEntity
import com.example.pizza_shift_2025.data.dbentity.SizeEntity

data class OrderedPizzaModel(
    val id: String,
    val name: String,
    val toppings: List<IngredientModel>,
    val size: SizeEntity,
    val doughs: DoughEntity
)