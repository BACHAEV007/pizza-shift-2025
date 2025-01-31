package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.state.PizzaDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class BasketViewModel(
    private val getBasketUseCase: GetBasketUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PizzaCatalogState>(PizzaCatalogState.Initial)
    val state: StateFlow<PizzaCatalogState> = _state.asStateFlow()

    init {
        loadCatalog()
    }

    fun loadCatalog() {
        viewModelScope.launch {
            getBasketUseCase()
                .onStart { _state.value = PizzaCatalogState.Loading }
                .catch { e -> _state.value = PizzaCatalogState.Failure(e.localizedMessage.orEmpty()) }
                .collect { catalog -> _state.value = PizzaCatalogState.Content(catalog) }
        }
    }
}