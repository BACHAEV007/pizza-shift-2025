package com.example.pizza_shift_2025.presentation.ui


import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun BottomNavigationBar(navController: NavController) {
    val listItems = listOf(
        BottomNavigationItem.CatalogScreen,
        BottomNavigationItem.OrderScreen,
        BottomNavigationItem.BasketScreen,
        BottomNavigationItem.ProfileScreen
    )

    NavigationBar(
        containerColor = Color.White
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon), contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 10.sp,
                        lineHeight = 11.72.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    }
}
