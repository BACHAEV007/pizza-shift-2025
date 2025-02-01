package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.pizza_shift_2025.domain.entity.PaymentAddress
import com.example.pizza_shift_2025.domain.entity.PaymentPerson
import com.example.pizza_shift_2025.presentation.state.CheckoutState
import com.example.pizza_shift_2025.presentation.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier,
    goToBackScreen: () -> Unit,
    navController: NavController,
    viewModel: CheckoutViewModel
) {
    val checkoutState by viewModel.state.collectAsState()

    val inputAddress = remember{ mutableStateOf<PaymentAddress?>(null)}
    val inputUser = remember{ mutableStateOf<PaymentPerson?>(null)}

    when (val state = checkoutState) {
        is CheckoutState.Initial,
        is CheckoutState.Loading -> LoadingComponent()

        is CheckoutState.FirstPart -> UserInfoCheckScreen(
            modifier = modifier,
            goToBackScreen,
            inputUser.value, inputAddress.value,
            onContinue = { person, address ->

                inputUser.value = person

                val parts = address.split(",").map { it.trim() }

                inputAddress.value = PaymentAddress(
                    street = parts.getOrNull(0) ?: "",
                    house = parts.getOrNull(1) ?: "",
                    apartment = parts.getOrNull(2) ?: "",
                    comment = parts.getOrNull(3)
                )
                viewModel.validateFirstPart(inputUser.value!!, inputAddress.value!!)
            })

        is CheckoutState.SecondPart -> CardScreen(modifier = modifier,
            onBack = {
                viewModel.goToFirstPart()
            },
            onContinue = {

                if(inputAddress.value != null && inputUser.value != null) {

                    viewModel.validateDebitCard(
                        address = inputAddress.value!!,
                        paymentPerson = inputUser.value!!,
                        card = it
                    )
                }
            })
        is CheckoutState.Failure -> ErrorComponent(message = state.message,
            onRetry = {
                viewModel.goToFirstPart()
            },)
        is CheckoutState.Success -> Unit
    }


}