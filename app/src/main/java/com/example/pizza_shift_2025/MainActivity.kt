package com.example.pizza_shift_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.pizza_shift_2025.data.mapper.PizzaMapper
import com.example.pizza_shift_2025.data.network.PizzaApi
import com.example.pizza_shift_2025.data.repository.BasketRepositoryImpl
import com.example.pizza_shift_2025.data.repository.PizzaRepositoryImpl
import com.example.pizza_shift_2025.domain.repository.BasketRepository
import com.example.pizza_shift_2025.domain.repository.PizzaRepository
import com.example.pizza_shift_2025.domain.usecase.AddPizzaToBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.navigation.MainScreen
import com.example.pizza_shift_2025.presentation.ui.BottomNavigationBar
import com.example.pizza_shift_2025.ui.theme.Pizzashift2025Theme

class MainActivity : ComponentActivity() {
    private val networkModule = NetworkModule()
    private val pizzaApi: PizzaApi = networkModule.retrofit.create(PizzaApi::class.java)
    private val dbMapper: PizzaMapper = PizzaMapper()
    private val BasketRepository: BasketRepository = BasketRepositoryImpl(pizzaApi, dbMapper)
    private val pizzaRepository: PizzaRepository = PizzaRepositoryImpl(pizzaApi)
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase = GetPizzaCatalogUseCase(pizzaRepository)
    private val getBasketUseCase: GetBasketUseCase = GetBasketUseCase(BasketRepository)
    private val addPizzaToBasketUseCase: AddPizzaToBasketUseCase = AddPizzaToBasketUseCase(BasketRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Pizzashift2025Theme{
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    MainScreen(
                        getPizzaCatalogUseCase,
                        getBasketUseCase,
                        addPizzaToBasketUseCase,
                        modifier = Modifier.padding(innerPadding).background(Color.White),
                        navController = navController
                    )
                }
            }
        }
    }
}

