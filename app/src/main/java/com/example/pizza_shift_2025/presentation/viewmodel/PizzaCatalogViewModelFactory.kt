package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase

class PizzaCatalogViewModelFactory (
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == PizzaCatalogViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return PizzaCatalogViewModel(getPizzaCatalogUseCase) as T
    }
}