package com.example.pizza_shift_2025.data.model

data class PizzaPaymentModel(
    val receiverAddress: PaymentAddressModel,
    val person: PaymentPersonModel,
    val debitCard: PaymentDebitCardModel,
    val pizzas: List<OrderedPizzaModel>
)