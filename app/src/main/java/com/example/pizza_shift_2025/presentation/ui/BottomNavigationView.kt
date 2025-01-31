package com.example.pizza_shift_2025.presentation.ui

import com.example.pizza_shift_2025.R

sealed class BottomNavigationItem(val title: String, val icon: Int, val route: String) {
    object CatalogScreen : BottomNavigationItem("Пицца", R.drawable.pizza_icon, "catalog_screen")
    object OrderScreen : BottomNavigationItem("Заказы", R.drawable.time_icon, "order_screen")
    object BasketScreen : BottomNavigationItem("Корзина", R.drawable.trash_icon, "basket_screen")
    object ProfileScreen : BottomNavigationItem("Профиль", R.drawable.user_icon, "profile_screen")
}