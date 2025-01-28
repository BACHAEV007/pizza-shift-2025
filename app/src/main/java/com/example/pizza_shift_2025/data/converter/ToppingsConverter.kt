package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.ToppingModel
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.Topping

fun ToppingModel.toDomainModel(): Topping {
    return Topping(
        name = this.name.toDomainModel(),
        cost = this.cost,
        img = this.img
    )
}