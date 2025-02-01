package com.example.pizza_shift_2025.data.model

data class OrderedPizzaModel(
    val id: String,
    val name: String,
    val toppings: List<IngredientModel>,
    val size: SizeModel,
    val doughs: DoughModel
)