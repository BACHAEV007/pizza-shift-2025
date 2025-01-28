package com.example.pizza_shift_2025.data.converter

import com.example.pizza_shift_2025.data.model.SizeTypeModel
import com.example.pizza_shift_2025.domain.entity.SizeType

fun SizeTypeModel.toDomainModel(): SizeType {
    return SizeType.valueOf(this.name)
}