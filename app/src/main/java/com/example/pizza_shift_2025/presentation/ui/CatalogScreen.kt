package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import com.example.pizza_shift_2025.presentation.viewmodel.PizzaCatalogViewModel

@Composable
fun CatalogScreen(
    viewModel: PizzaCatalogViewModel,
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit
) {
    val catalogState by viewModel.state.collectAsStateWithLifecycle()

    CatalogScreenInternal(catalogState, modifier, onItemSelected)

}

@Composable
private fun CatalogScreenInternal(
    state: PizzaCatalogState,
    modifier: Modifier,
    onItemSelected: (String) -> Unit
) {

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            text = stringResource(R.string.pizza_title),
            style = MaterialTheme.typography.labelLarge,
            fontSize = 32.sp
        )

        when (state) {
            is PizzaCatalogState.Loading -> LoadingComponent()
            is PizzaCatalogState.Content -> CatalogContentComponent(
                catalog = state.pizzaCatalog,
                onItemClicked = onItemSelected
            )

            is PizzaCatalogState.Initial -> Unit
            is PizzaCatalogState.Failure -> Unit
        }
    }
}