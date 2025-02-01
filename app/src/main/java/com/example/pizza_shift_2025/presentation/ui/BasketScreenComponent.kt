package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Pizza

@Composable
fun BasketScreenComponent(
    basketList: List<BasketPizza>,
    onIncreasePizzaQuantity: (String) -> Unit,
    onDecreasePizzaQuantity: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        itemsIndexed(basketList) { index, pizza ->
            Spacer(modifier = Modifier.size(24.dp))
            BasketItem(
                modifier = Modifier
                    .clickable(enabled = false) {}
                    .background(Color.Transparent),
                pizza,
                onIncreasePizzaQuantity = onIncreasePizzaQuantity,
                onDecreasePizzaQuantity = onDecreasePizzaQuantity
            )
            if (index < basketList.lastIndex) {
                Spacer(modifier = Modifier.size(24.dp))
                Divider(
                    color = colorResource(id = R.color.extra_light_grey),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}

@Composable
fun BasketItem(
    modifier: Modifier,
    pizza: BasketPizza,
    onIncreasePizzaQuantity: (String) -> Unit,
    onDecreasePizzaQuantity: (String) -> Unit
) {
    val painter = rememberImagePainter(pizza.img)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
//            .clickable {
//                onIncreasePizzaQuantity(pizza.id)
//            }
    ) {
        Image(
            painter = painter,
            contentDescription = "Pizza Image",
            modifier = Modifier
                .size(66.dp)
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
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                Row(
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.click_button),
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.minus),
                        fontSize = 12.sp
                    )

                    Text(
                        text = "1",
                        fontSize = 12.sp
                    )

                    Text(
                        text = stringResource(R.string.plus),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.edit),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    textDecoration = TextDecoration.Underline,
                    color = colorResource(id = R.color.underline_text)

                )
                Spacer(modifier = Modifier.size(32.dp))
                Text(
                    text = stringResource(R.string.cost, pizza.price),
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 16.sp,
                    lineHeight = 16.sp

                )
            }


        }

    }
}