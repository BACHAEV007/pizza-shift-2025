package com.example.pizza_shift_2025.domain.repository

import com.example.pizza_shift_2025.domain.entity.Pizza

interface PizzaRepository {
    suspend fun getPizzaCatalog(): List<Pizza>
}