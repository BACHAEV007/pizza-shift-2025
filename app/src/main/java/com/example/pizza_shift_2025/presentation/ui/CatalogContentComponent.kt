package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pizza_shift_2025.domain.entity.Pizza

@Composable
fun CatalogContentComponent(
    catalog: List<Pizza>,
    onItemClicked: () -> Unit
){
    LazyColumn (
        modifier = Modifier.fillMaxHeight()
    ){
        items(catalog) { pizza ->
            Spacer(modifier = Modifier.size(24.dp))
            PizzaItem(
                modifier = Modifier,
                pizza,
                onItemClicked = { }
            )
        }
    }
}

@Composable
fun PizzaItem(
    modifier: Modifier,
    pizza: Pizza,
    onItemClicked: () -> Unit
) {
    val painter = rememberImagePainter(pizza.img)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClicked()
            }
    ) {
        println("${pizza.img}")
        Image(
            painter = painter,
            contentDescription = "Pizza Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

    }
}