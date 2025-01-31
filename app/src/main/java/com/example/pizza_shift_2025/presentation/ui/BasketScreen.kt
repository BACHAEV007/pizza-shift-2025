package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.state.PizzaDetailState
import com.example.pizza_shift_2025.presentation.viewmodel.BasketViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel

@Composable
fun BasketScreen(
    modifier: Modifier = Modifier,
    viewModel: BasketViewModel,
    orderButtonSelected: () -> Unit,
    goToCatalogScreen: () -> Unit,

) {
    val pizzaState by viewModel.state.collectAsState()

    fun onIncreasePizzaQuantity(pizzaId: String) {
//        viewModel.increasePizzaQuantity(pizzaId)
    }

    fun onDecreasePizzaQuantity(pizzaId: String) {
//        viewModel.decreasePizzaQuantity(pizzaId)
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 128.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { goToCatalogScreen() }, modifier = Modifier.size(24.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = "Arrow Left",
                        tint = colorResource(R.color.light_gray)
                    )
                }
                Spacer(Modifier.size(32.dp))
                Text(
                    text = stringResource(R.string.basket),
                    style = MaterialTheme.typography.labelLarge,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )
            }

            when (val state = pizzaState) {
                is PizzaCatalogState.Initial,
                is PizzaCatalogState.Loading -> LoadingComponent()
                is PizzaCatalogState.Content -> BasketScreenComponent (
                    basketList = state.pizzaCatalog,
                    onIncreasePizzaQuantity = ::onIncreasePizzaQuantity,
                    onDecreasePizzaQuantity = ::onDecreasePizzaQuantity
                )
                is PizzaCatalogState.Failure -> Unit
            }
        }

        OrderSummaryBar(
            orderPrice = 1240,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            orderButtonSelected
        )
    }
}

@Composable
fun OrderSummaryBar(orderPrice: Int, modifier: Modifier = Modifier, orderButtonSelected: () -> Unit,) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(16.dp)

    ) {
        Row(){
            Text(
                text = stringResource(R.string.order_cost),
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W500,

                )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = stringResource(R.string.order_price, orderPrice),
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelMedium,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W500,

                )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { orderButtonSelected() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.orange)
            )
        ) {
            Text(text = stringResource(R.string.setup_order), color = Color.White, fontSize = 16.sp)
        }
    }
}