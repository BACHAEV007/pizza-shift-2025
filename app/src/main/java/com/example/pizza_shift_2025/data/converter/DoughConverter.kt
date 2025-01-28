package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.DoughModel
import com.example.pizza_shift_2025.domain.entity.Dough

fun DoughModel.toDomainModel(): Dough {

    return Dough(
        name = this.name.toDomainModel(),
        price = this.price
    )
}