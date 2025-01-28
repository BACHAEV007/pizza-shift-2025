package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.IngredientModel
import com.example.pizza_shift_2025.data.model.IngredientTypeModel
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.IngredientType

fun IngredientTypeModel.toDomainModel(): IngredientType {
    return IngredientType.valueOf(this.name)
}