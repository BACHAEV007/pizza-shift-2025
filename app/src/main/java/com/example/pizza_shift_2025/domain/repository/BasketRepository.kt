package com.example.pizza_shift_2025.domain.repository

import com.example.pizza_shift_2025.domain.entity.Pizza
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun addPizzaToBasket(pizza: Pizza)
    fun getBasket() : Flow<List<Pizza>>
}