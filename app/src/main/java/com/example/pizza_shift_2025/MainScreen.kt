package com.example.pizza_shift_2025

import androidx.compose.runtime.Composable
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.ui.CatalogScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModelFactory

@Composable
fun MainScreen(
    getPizzaCatalogUseCase: GetPizzaCatalogUseCase
) {
    val viewModel: PizzaCatalogViewModel = viewModel(
        factory = PizzaCatalogViewModelFactory(getPizzaCatalogUseCase)
    )
    CatalogScreen(viewModel)
}