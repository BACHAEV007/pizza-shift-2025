package com.example.pizza_shift_2025

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.ui.CatalogScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_2025.presentation.ui.DetailScreen
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModelFactory
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModelFactory
import java.lang.Class

@Composable
fun MainScreen(
    getPizzaCatalogUseCase: GetPizzaCatalogUseCase,
    modifier: Modifier = Modifier
) {
    val viewModel: PizzaCatalogViewModel = viewModel(
        factory = PizzaCatalogViewModelFactory(getPizzaCatalogUseCase)
    )
    val viewModelD: PizzaDetailViewModel = viewModel(
        factory = PizzaDetailViewModelFactory(getPizzaCatalogUseCase)
    )
    DetailScreen(modifier, viewModelD, onItemSelected = {})
}