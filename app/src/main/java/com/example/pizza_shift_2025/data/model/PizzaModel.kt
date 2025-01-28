package com.example.pizza_shift_2025.data.model

data class PizzaModel(
    val id: String,
    val name: String,
    val ingredients: List<IngredientModel>,
    val toppings: List<IngredientModel>,
    val description: String,
    val sizes: List<SizeModel>,
    val doughs: List<DoughModel>,
    val calories: Int,
    val protein: String,
    val totalFat: String,
    val carbohydrates: String,
    val sodium: String,
    val allergens: List<String>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isNew: Boolean,
    val isHit: Boolean,
    val img: String
)