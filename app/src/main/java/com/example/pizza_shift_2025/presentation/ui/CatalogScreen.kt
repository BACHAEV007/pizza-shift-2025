package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    viewModel: PizzaCatalogViewModel,
    onItemSelected: (String) -> Unit
) {
    val catalogState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCatalog()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            text = stringResource(R.string.pizza_title),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 32.sp
        )

        when (val state = catalogState) {
            is PizzaCatalogState.Initial,
            is PizzaCatalogState.Loading -> LoadingComponent()

            is PizzaCatalogState.Content -> CatalogContentComponent(
                catalog = state.pizzaCatalog,
                onItemClicked = onItemSelected
            )
            is PizzaCatalogState.Failure -> Unit
        }
    }

}