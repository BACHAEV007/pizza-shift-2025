package com.example.pizza_shift_2025.domain.entity

data class PaymentAddress(
    val street: String,
    val house: String,
    val apartment: String,
    val comment: String?
)