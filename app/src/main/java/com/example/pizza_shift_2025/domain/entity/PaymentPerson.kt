package com.example.pizza_shift_2025.domain.entity

data class PaymentPerson(
    val firstName: String,
    val lastName: String,
    val middleName: String? = null,
    val phone: String
)