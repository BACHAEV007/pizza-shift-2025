package com.example.pizza_shift_2025.data.repository

import com.example.pizza_shift_2025.data.converter.toDomainModel
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.PizzaRepository

class PizzaRepositoryImpl(
    private val pizzaApi: PizzaApi
): PizzaRepository {
    override suspend fun getPizzaCatalog(): List<Pizza> {
        return (pizzaApi.getPizzaCatalog().map { it.toDomainModel() })
    }
}