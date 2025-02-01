package com.example.pizza_shift_2025.domain.entity

data class PaymentDebitCard(
    val pan: String,
    val expireDate: String,
    val cvv: String
)
