package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.constants.Constants.IMG_URL
import com.example.pizza_shift_2025.data.model.IngredientModel
import com.example.pizza_shift_2025.domain.entity.Ingredient

fun IngredientModel.toDomainModel(): Ingredient {
    return Ingredient(
        name = this.name.toDomainModel(),
        cost = this.cost,
        img = IMG_URL + this.img
    )
}