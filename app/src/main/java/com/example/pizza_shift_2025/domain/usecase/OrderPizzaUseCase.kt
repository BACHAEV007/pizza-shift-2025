package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.PizzaPayment
import com.example.pizza_shift_2025.domain.repository.PizzaRepository

class OrderPizzaUseCase(private val repository: PizzaRepository) {
    suspend operator fun invoke(pizzaPayment: PizzaPayment) =
        repository.orderPizza(pizzaPayment)
}