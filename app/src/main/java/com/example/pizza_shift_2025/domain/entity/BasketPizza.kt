package com.example.pizza_shift_2025.domain.entity

data class BasketPizza (
    val name: String,
    val price: Int,
    val img: String,
    val description: String,
    val toppings: List<Ingredient>,
    val size: Size,
    val doughs: Dough
)