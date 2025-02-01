package com.example.pizza_shift_2025.presentation.state

import com.example.pizza_shift_2025.domain.entity.PizzaPayment

sealed interface CheckoutState {

    data object Initial : CheckoutState

    data object Loading : CheckoutState

    data object FirstPart : CheckoutState

    data object SecondPart : CheckoutState

    data class Success(
        val paymentPrice: String,
        val description: String,
        val pizzaPayment: PizzaPayment
    ) : CheckoutState

    data class Failure(val message: String) : CheckoutState
}