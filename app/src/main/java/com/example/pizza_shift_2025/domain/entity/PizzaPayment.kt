package com.example.pizza_shift_2025.domain.entity

data class PizzaPayment(
    val receiverAddress: PaymentAddress,
    val person: PaymentPerson,
    val debitCard: PaymentDebitCard,
    val pizzas: List<BasketPizza>
)