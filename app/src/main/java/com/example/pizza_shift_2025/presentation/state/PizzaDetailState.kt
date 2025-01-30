package com.example.pizza_shift_2025.presentation.state

import com.example.pizza_shift_2025.domain.entity.Pizza

sealed interface PizzaDetailState {
    data object Initial : PizzaDetailState
    data object Loading : PizzaDetailState
    data class Failure(val message: String?) : PizzaDetailState
    data class Content(val detail: Pizza) : PizzaDetailState
}