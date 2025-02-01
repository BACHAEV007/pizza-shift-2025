package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow

class GetBasketUseCase(private val repository: BasketRepository) {
    operator fun invoke(): Flow<List<BasketPizza>> = repository.getBasket()
}