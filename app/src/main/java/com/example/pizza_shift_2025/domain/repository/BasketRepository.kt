package com.example.pizza_shift_2025.domain.repository

import com.example.pizza_shift_2025.domain.entity.Pizza

interface BasketRepository {
    suspend fun addPizzaToBasket(pizza: Pizza)
    suspend fun getBasket() : List<Pizza>
}