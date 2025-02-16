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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.domain.usecase.OrderPizzaUseCase
import com.example.pizza_shift_2025.presentation.navigation.MainScreen
import com.example.pizza_shift_2025.presentation.ui.BottomNavigationBar
import com.example.pizza_shift_2025.ui.theme.Pizzashift2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Pizzashift2025Theme{
                val navController = rememberNavController()
                val shouldShowBottomBar = remember { mutableStateOf(true) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar.value) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding).background(Color.White),
                        navController = navController,
                        onBottomBarVisibilityChanged = { isVisible -> shouldShowBottomBar.value = isVisible }
                    )
                }
            }
        }
    }
}

