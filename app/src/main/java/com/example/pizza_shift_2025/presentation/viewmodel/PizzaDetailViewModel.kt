package com.example.pizza_shift_2025.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Dough
import com.example.pizza_shift_2025.domain.entity.DoughType
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.Size
import com.example.pizza_shift_2025.domain.entity.SizeType
import com.example.pizza_shift_2025.domain.usecase.AddPizzaToBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.state.PizzaDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class PizzaDetailViewModel(
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase,
    private val addPizzaToBasketUseCase: AddPizzaToBasketUseCase
): ViewModel() {

    private val _state = MutableStateFlow<PizzaDetailState>(PizzaDetailState.Initial)
    val state: StateFlow<PizzaDetailState> = _state

    fun getPizza(id: String) {
        viewModelScope.launch {
            _state.value = PizzaDetailState.Loading

            try {
                val catalog = getPizzaCatalogUseCase()
                val pizza = catalog.find { it.id == id }

                if (pizza != null) {
                    _state.value = PizzaDetailState.Content(pizza)
                } else {
                    _state.value = PizzaDetailState.Failure("Pizza not found.")
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = PizzaDetailState.Failure(ex.localizedMessage.orEmpty())
            }
        }
    }
    fun addToBasket(pizza: Pizza, size: Size, dough: Dough, toppings: List<Ingredient>) {

        viewModelScope.launch {
            try {
                val price = calculatePrice(size, dough, toppings)
                addPizzaToBasketUseCase(
                    BasketPizza(
                        name = pizza.name,
                        description = pizza.description,
                        price = price,
                        toppings = toppings,
                        size = size,
                        doughs = dough,
                        img = pizza.img
                    )
                )

            } catch (e: Exception) {

            }
        }
    }

    private fun calculatePrice(size: Size, dough: Dough, toppings: List<Ingredient>): Int {
        var totalPrice = size.price + dough.price
        toppings.forEach { totalPrice += it.cost }
        return totalPrice
    }
}