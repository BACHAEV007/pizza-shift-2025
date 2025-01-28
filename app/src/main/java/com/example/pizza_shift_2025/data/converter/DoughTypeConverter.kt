package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.DoughTypeModel
import com.example.pizza_shift_2025.domain.entity.DoughType

fun DoughTypeModel.toDomainModel(): DoughType {
    return DoughType.valueOf(this.name)
}