package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.repository.BasketRepository


class AddPizzaToBasketUseCase (private val repository: BasketRepository) {
    suspend operator fun invoke(pizza: BasketPizza) =
        repository.addPizzaToBasket(pizza)
}