package com.example.pizza_shift_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.data.repository.PizzaRepositoryImpl
import com.example.pizza_shift_2025.domain.repository.PizzaRepository
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.ui.theme.Pizzashift2025Theme

class MainActivity : ComponentActivity() {
    private val networkModule = NetworkModule()
    private val pizzaApi = networkModule.retrofit.create(PizzaApi::class.java)
    private val pizzaRepository: PizzaRepository = PizzaRepositoryImpl(pizzaApi)
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase = GetPizzaCatalogUseCase(pizzaRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pizzashift2025Theme {

            }
        }
    }
}

