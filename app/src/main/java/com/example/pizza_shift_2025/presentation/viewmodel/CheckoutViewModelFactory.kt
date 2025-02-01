package com.example.pizza_shift_2025.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase

class CheckoutViewModelFactory (
    private val getBasketUseCase: GetBasketUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == CheckoutViewModel::class.java) { "Unknown ViewModel: $modelClass" }
        return CheckoutViewModel(getBasketUseCase) as T
    }
}