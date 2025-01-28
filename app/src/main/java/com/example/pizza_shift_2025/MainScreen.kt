package com.example.pizza_shift_2025

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.ui.CatalogScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModelFactory
import java.lang.Class

@Composable
fun MainScreen(
    getPizzaCatalogUseCase: GetPizzaCatalogUseCase,
    modifier: Modifier = Modifier
) {
    val viewModel: PizzaCatalogViewModel = viewModel(
        factory = PizzaCatalogViewModelFactory(getPizzaCatalogUseCase)
    )
    CatalogScreen(modifier, viewModel, onItemSelected = {})
}