package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.PizzaRepository

class GetPizzaCatalogUseCase(private val repository: PizzaRepository) {
    suspend operator fun invoke(): List<Pizza> =
        repository.getPizzaCatalog()

}