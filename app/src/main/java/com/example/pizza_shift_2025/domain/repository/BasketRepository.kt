package com.example.pizza_shift_2025.domain.repository

import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    suspend fun addPizzaToBasket(pizza: BasketPizza)
    fun getBasket() : Flow<List<BasketPizza>>
}