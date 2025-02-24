package com.example.pizza_shift_2025.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_shift_2025.domain.usecase.GetPizzaCatalogUseCase
import com.example.pizza_shift_2025.presentation.state.PizzaCatalogState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@Stable
class PizzaCatalogViewModel(
    private val getPizzaCatalogUseCase: GetPizzaCatalogUseCase
): ViewModel() {

    private val _state = MutableStateFlow<PizzaCatalogState>(PizzaCatalogState.Initial)
    val state: StateFlow<PizzaCatalogState> = _state
    init {
        loadCatalog()
    }

    fun loadCatalog() {
        viewModelScope.launch {
            _state.value = PizzaCatalogState.Loading

            try {
                val catalog = getPizzaCatalogUseCase()
                _state.value = PizzaCatalogState.Content(catalog)
            } catch (ce: CancellationException) {
                throw ce
            } catch (ex: Exception) {
                _state.value = PizzaCatalogState.Failure(ex.localizedMessage.orEmpty())
            }
        }
    }
}