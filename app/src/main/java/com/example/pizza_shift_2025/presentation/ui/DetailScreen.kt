package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.state.PizzaDetailState
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaDetailViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PizzaDetailViewModel,
    onItemSelected: () -> Unit
) {
    val pizzaState by viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadCatalog()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { Unit }, modifier = Modifier.size(24.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Arrow Left",
                    tint = colorResource(R.color.light_gray)
                )
            }
            Spacer(Modifier.size(32.dp))
            Text(

                text = stringResource(R.string.pizza_title),
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp
            )

        }


        when (val state = pizzaState) {
            is PizzaDetailState.Initial,
            is PizzaDetailState.Loading -> LoadingComponent()

            is PizzaDetailState.Content -> DetailScreenComponent(
            body = state.detail,
            onItemClicked = onItemSelected
        )
            is PizzaDetailState.Failure -> Unit
        }
    }
}