package com.example.pizza_shift_2025.data.model

data class PizzaModel(
    val allergens: List<String>,
    val calories: Int,
    val carbohydrates: String,
    val description: String,
    val doughs: List<DoughModel>,
    val id: String,
    val img: String,
    val ingredients: List<IngredientModel>,
    val isGlutenFree: Boolean,
    val isHit: Boolean,
    val isNew: Boolean,
    val isVegetarian: Boolean,
    val name: String,
    val protein: String,
    val sizes: List<SizeModel>,
    val sodium: String,
    val toppings: List<ToppingModel>,
    val totalFat: String
)