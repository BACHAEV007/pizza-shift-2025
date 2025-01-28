package com.example.pizza_shift_2025.presentation.state

import com.example.pizza_shift_2025.domain.entity.Pizza

sealed interface PizzaCatalogState {
    data object Initial : PizzaCatalogState
    data object Loading : PizzaCatalogState
    data class Failure(val message: String?) : PizzaCatalogState
    data class Content(val pizzaCatalog: List<Pizza>) : PizzaCatalogState
}