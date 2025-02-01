package com.example.pizza_shift_2025.presentation.screen

sealed class Screen (val route: String){
    object CatalogScreen: Screen(route = "catalog_screen")
    object PizzaDetailScreen: Screen(route = "pizza_detail_screen/{id}") {
        fun createRoute(id: String) = "pizza_detail_screen/$id"
    }
    object BasketScreen : Screen(route = "basket_screen")
    object ProfileScreen : Screen(route = "profile_screen")
    object OrderScreen : Screen(route = "order_screen")
    object CheckoutScreen : Screen(route = "checkout_screen")
}