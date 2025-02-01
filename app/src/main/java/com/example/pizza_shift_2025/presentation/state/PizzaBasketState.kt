package com.example.pizza_shift_2025.presentation.state

import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza

sealed interface PizzaBasketState {
    data object Initial : PizzaBasketState
    data object Loading : PizzaBasketState
    data class Failure(val message: String?) : PizzaBasketState
    data class Content(val pizzaBasket: List<BasketPizza>, val totalPrice: Int) : PizzaBasketState
}