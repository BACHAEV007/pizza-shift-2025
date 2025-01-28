package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.SizeModel
import com.example.pizza_shift_2025.domain.entity.Size

fun SizeModel.toDomainModel(): Size {
    return Size(
        name = this.name.toDomainModel(),
        price = this.price
    )
}
