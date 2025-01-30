package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.domain.entity.Pizza

@Composable
fun CatalogContentComponent(
    catalog: List<Pizza>,
    onItemClicked: (String) -> Unit
){
    LazyColumn (
        modifier = Modifier.fillMaxHeight()
    ){
        items(catalog) { pizza ->
            Spacer(modifier = Modifier.size(24.dp))
            PizzaItem(
                modifier = Modifier,
                pizza,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Composable
fun PizzaItem(
    modifier: Modifier,
    pizza: Pizza,
    onItemClicked: (String) -> Unit
) {
    val painter = rememberImagePainter(pizza.img)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable {
                onItemClicked(pizza.id)
            }
    ) {
        Image(
            painter = painter,
            contentDescription = "Pizza Image",
            modifier = Modifier
                .size(116.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(26.dp))
        Column {
            Text(
                text = pizza.name,
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                lineHeight = 24.sp
            )
            Text(
                text = pizza.description,
                modifier = Modifier.padding(bottom = 8.dp),
                color = colorResource(id = R.color.gray),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
            Text(
                text = stringResource(R.string.pizza_cost, pizza.sizes[0].price),
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.labelMedium,
                fontSize = 16.sp,
                lineHeight = 16.sp

            )

        }

    }
}