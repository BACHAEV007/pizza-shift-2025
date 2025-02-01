package com.example.pizza_shift_2025.domain.usecase

import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza

class CountPizzaPriceUseCase {

    operator fun invoke(pizza: BasketPizza): Int {
        val toppingsPrice = pizza.toppings.sumOf { it.cost }
        val sizePrice = pizza.size.price
        val doughPrice = pizza.doughs.price

        return toppingsPrice + sizePrice + doughPrice
    }
}