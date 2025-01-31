package com.example.pizza_shift_2025.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.entity.Pizza
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
    fun addToBasket(pizza: Pizza) {
        Log.d("PizzaDetailViewModel", "addToBasket вызвана с пиццей: $pizza")
        viewModelScope.launch {
            try {
                Log.d("PizzaDetailViewModel", "Запуск добавления пиццы в корзину...")
                addPizzaToBasketUseCase(pizza)
                Log.d("PizzaDetailViewModel", "Пицца успешно добавлена в корзину.")
            } catch (e: Exception) {
                Log.e("PizzaDetailViewModel", "Ошибка при добавлении пиццы в корзину: ${e.localizedMessage}")
            }
        }
    }
}