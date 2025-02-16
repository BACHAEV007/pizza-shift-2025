package com.example.pizza_shift_2025.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.ui.CatalogScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pizza_shift_2025.domain.usecase.AddPizzaToBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.CountPizzaPriceUseCase
import com.example.pizza_shift_2025.domain.usecase.GetBasketUseCase
import com.example.pizza_shift_2025.domain.usecase.OrderPizzaUseCase
import com.example.pizza_shift_2025.presentation.screen.Screen
import com.example.pizza_shift_2025.presentation.ui.BasketScreen
import com.example.pizza_shift_2025.presentation.ui.CheckoutScreen
import com.example.pizza_shift_2025.presentation.ui.DetailScreen
import com.example.pizza_shift_2025.presentation.ui.OrderScreen
import com.example.pizza_shift_2025.presentation.ui.ProfileScreen
import com.example.pizza_shift_2025.presentation.viewmodel.BasketViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.CheckoutViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBottomBarVisibilityChanged: (Boolean) -> Unit
) {

    NavHost(navController = navController, startDestination = Screen.CatalogScreen.route) {
        composable(Screen.CatalogScreen.route) {
            onBottomBarVisibilityChanged(true)
            val viewModel: PizzaCatalogViewModel = koinViewModel()
            CatalogScreen(
                modifier = modifier,
                viewModel = viewModel,
                onItemSelected = { pizzaId ->
                    navController.navigate(Screen.PizzaDetailScreen.createRoute(pizzaId))
                },
            )
        }
        composable(
            route = Screen.PizzaDetailScreen.route,
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            onBottomBarVisibilityChanged(true)
            val pizzaId = backStackEntry.arguments?.getString("id") ?: return@composable

            val viewModel: PizzaDetailViewModel = koinViewModel()

            DetailScreen(
                modifier,
                viewModel,
                pizzaId,
                goToCatalogScreen = {
                    navController.navigate(Screen.CatalogScreen.route) {
                        popUpTo(Screen.CatalogScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.OrderScreen.route) {
            OrderScreen()
        }
        composable(Screen.BasketScreen.route) {
            onBottomBarVisibilityChanged(true)
            val viewModel: BasketViewModel = koinViewModel()
            BasketScreen(
                modifier,
                viewModel,
                orderButtonSelected = {

                    navController.navigate(Screen.CheckoutScreen.route) { onBottomBarVisibilityChanged(false)
                    popUpTo(Screen.CheckoutScreen.route) {
                        inclusive = true

                    }
                        onBottomBarVisibilityChanged(false)
                }

                },
                goToCatalogScreen = {
                    navController.navigate(Screen.CatalogScreen.route) {
                        popUpTo(Screen.CatalogScreen.route) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(Screen.CheckoutScreen.route){
            val viewModel: CheckoutViewModel = koinViewModel()
            onBottomBarVisibilityChanged(false)
            CheckoutScreen(
                modifier = modifier,
                goToBackScreen = {

                    navController.navigate(Screen.BasketScreen.route) {
                        popUpTo(Screen.BasketScreen.route) {
                            inclusive = true

                        }
                        onBottomBarVisibilityChanged(true)
                    }

                },
                navController,
                viewModel
            )
        }
    }

}


