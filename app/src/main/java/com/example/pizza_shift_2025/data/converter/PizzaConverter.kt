package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.PizzaModel
import com.example.pizza_shift_2025.domain.entity.Pizza

fun PizzaModel.toDomainModel(): Pizza {
    return Pizza(
        id = this.id,
        name = this.name,
        ingredients = this.ingredients.map { it.toDomainModel() },
        toppings = this.toppings.map { it.toDomainModel() },
        description = this.description,
        sizes = this.sizes.map { it.toDomainModel() },
        doughs = this.doughs.map { it.toDomainModel() },
        calories = this.calories,
        protein = this.protein,
        totalFat = this.totalFat,
        carbohydrates = this.carbohydrates,
        sodium = this.sodium,
        allergens = this.allergens,
        isVegetarian = this.isVegetarian,
        isGlutenFree = this.isGlutenFree,
        isNew = this.isNew,
        isHit = this.isHit,
        img = "https://shift-intensive.ru/api/" + this.img
    )
}