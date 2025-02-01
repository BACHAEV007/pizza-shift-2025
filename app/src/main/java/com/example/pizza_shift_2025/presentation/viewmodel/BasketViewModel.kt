package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.presentation.state.PizzaBasketState
import com.example.pizza_shift_2025.presentation.state.PizzaDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class BasketViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val countPizzaPriceUseCase: CountPizzaPriceUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PizzaBasketState>(PizzaBasketState.Initial)
    val state: StateFlow<PizzaBasketState> = _state.asStateFlow()

    init {
        loadBasket()
    }

    fun loadBasket() {
        viewModelScope.launch {
            getBasketUseCase()
                .onStart { _state.value = PizzaBasketState.Loading }
                .catch { e -> _state.value = PizzaBasketState.Failure(e.localizedMessage.orEmpty()) }
                .collect { basket ->
                    val totalPrice = basket.sumOf { countPizzaPriceUseCase(it) }
                    _state.value = PizzaBasketState.Content(basket, totalPrice)
                }
        }
    }
}