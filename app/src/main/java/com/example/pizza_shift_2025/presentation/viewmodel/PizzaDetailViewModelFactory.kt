package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.usecase.AddPizzaToBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class PizzaDetailViewModelFactory(
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase,
    private val addPizzaToBasketUseCase: AddPizzaToBasketUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PizzaDetailViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return PizzaDetailViewModel(getPizzaCatalogUseCase, addPizzaToBasketUseCase) as T
    }
}