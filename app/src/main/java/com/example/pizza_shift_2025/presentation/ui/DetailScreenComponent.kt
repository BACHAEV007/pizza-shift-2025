package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.domain.entity.BasketPizza
import com.example.pizza_shift_2025.domain.entity.Ingredient
import com.example.pizza_shift_2025.domain.entity.Pizza
import com.example.pizza_shift_2025.domain.entity.Size
import com.example.pizza_shift_2025.domain.entity.SizeType
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel

@Composable
fun DetailScreenComponent(
    pizzaState: Pizza,
    viewModel: PizzaDetailViewModel
) {

    var selectedSize by remember { mutableStateOf(pizzaState.sizes[0]) }
    var selectedToppings by remember { mutableStateOf(emptyList<Ingredient>()) }
    val selectedDough by remember { mutableStateOf(pizzaState.doughs[0]) }
    var activeIndex by remember { mutableStateOf(0) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(3) }) {
            Spacer(modifier = Modifier.size(24.dp))
        }
        item(span = { GridItemSpan(3) }) {
            val painter = rememberImagePainter(pizzaState.img)
            Column {
                Image(
                    painter = painter,
                    contentDescription = "Pizza Image",
                    modifier = Modifier
                        .size(220.dp)
                        .aspectRatio(1f)
                        .align(Alignment.CenterHorizontally)

                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        item(span = { GridItemSpan(3) }) {
            Column {
                DetailDescription(pizzaState, modifier = Modifier, activeIndex)
                }
        }
        item(span = { GridItemSpan(3) }) {
            LabelRow(
                pizzaState,
                activeIndex = activeIndex,
                onSizeSelected = { size -> selectedSize = size },
                onIndexChanged = { index ->
                    activeIndex = index
                }
            )
        }
        item(span = { GridItemSpan(3) }) {
            Spacer(modifier = Modifier.size(6.dp))
        }
        item(span = { GridItemSpan(3) }) {

            Button(
                onClick = { viewModel.addToBasket(pizzaState, selectedSize, selectedDough, selectedToppings) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.orange)
                )
            ) {
                Text(text = "Добавить в корзину", color = Color.White, fontSize = 16.sp)
            }
        }
        item(span = { GridItemSpan(3) }) {
            Spacer(modifier = Modifier.size(6.dp))
        }
        item(span = { GridItemSpan(3) }) {
            Text(
                text = stringResource(R.string.add_like_taste),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }
        items(pizzaState.toppings) { ingredient ->
            IngredientItem(
                ingredient = ingredient,
                isSelected = selectedToppings.contains(ingredient),
                onIngredientClick = {
                    selectedToppings = if (selectedToppings.contains(ingredient)) {
                        selectedToppings - ingredient
                    } else {
                        selectedToppings + ingredient
                    }
                }
            )
        }
    }
}


@Composable
fun DetailDescription(body: Pizza, modifier: Modifier, index: Int){
    val sizeLabels = listOf("25 см", "30 см", "35 см")
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = body.name,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 24.sp,
            style = MaterialTheme.typography.labelLarge,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = sizeLabels.get(index) + ", традиционное тесто",
            modifier = Modifier.padding(bottom = 8.dp),
            color = colorResource(id = R.color.gray),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
        Text(
            text = body.description,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.gray),
            fontSize = 16.sp,
            lineHeight = 24.sp

        )

    }
}

@Composable
fun LabelRow(
    pizzaState: Pizza,
    activeIndex: Int,
    onSizeSelected: (Size) -> Unit,
    onIndexChanged: (Int) -> Unit
) {
    var activeIndex by remember { mutableStateOf(0) }
    val sizeLabels =
        listOf(
            stringResource(R.string.small),
            stringResource(R.string.medium),
            stringResource(R.string.large)
        )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(14.dp))
    ) {
        pizzaState.sizes.forEachIndexed { index, size ->
            LabelButton(
                text = sizeLabels.getOrElse(index) { "" },
                isActive = index == activeIndex,
                modifier = Modifier.weight(1f),
                onClick = {
                    activeIndex = if (activeIndex == index) -1 else index
                    onSizeSelected(size)
                    onIndexChanged(index)
                }
            )
        }
    }
}

@Composable
fun LabelButton(text: String, isActive: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = when {
                isActive -> Color.White
                else -> Color(0xFFF3F4F6)
            },
            contentColor = when {
                isActive -> Color.Black
                else -> Color.Gray
            }
        ),
        contentPadding = PaddingValues(16.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        modifier = modifier.padding(4.dp)
    ) {
        Text(text = text, fontSize = 14.sp,
            lineHeight = 14.sp,
            style = MaterialTheme.typography.bodySmall)
    }
}