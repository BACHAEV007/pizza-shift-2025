package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.BasketRepository
import com.example.pizza_shift_2025.domain.repository.PizzaRepository

class AddPizzaToBasketUseCase (private val repository: BasketRepository) {
    suspend operator fun invoke(pizza: Pizza) =
        repository.addPizzaToBasket(pizza)
}