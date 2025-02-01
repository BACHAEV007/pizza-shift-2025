package com.example.pizza_shift_2025.domain.repository

import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.PizzaPayment

interface PizzaRepository {
    suspend fun getPizzaCatalog(): List<Pizza>
    suspend fun orderPizza(pizza: PizzaPayment)
}