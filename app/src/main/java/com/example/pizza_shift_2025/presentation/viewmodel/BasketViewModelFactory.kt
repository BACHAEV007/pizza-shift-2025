package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase

class BasketViewModelFactory (
    private val getBasketUseCase: GetBasketUseCase,
    private val countPizzaPriceUseCase: CountPizzaPriceUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == BasketViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return BasketViewModel(getBasketUseCase, countPizzaPriceUseCase) as T
    }
}