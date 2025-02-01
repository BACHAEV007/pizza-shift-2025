package com.example.pizza_shift_2025.data.repository

import android.util.Log
import com.example.pizza_shift_2025.data.converter.toDomainModel
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.PizzaPayment
import com.example.pizza_shift_2025.domain.repository.PizzaRepository

class PizzaRepositoryImpl(
    private val pizzaApi: PizzaApi
): PizzaRepository {
    override suspend fun getPizzaCatalog(): List<Pizza> =
        (pizzaApi.getPizzaCatalog().catalog.map { it.toDomainModel() })

    override suspend fun orderPizza(pizza: PizzaPayment) {
        pizzaApi.orderPizza(pizza)
    }
}