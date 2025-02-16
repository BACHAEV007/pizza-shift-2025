package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.domain.entity.PaymentAddress
import com.example.pizza_shift_2025.domain.entity.PaymentPerson

@Composable
fun UserInfoCheckScreen(
    modifier: Modifier = Modifier,
    goToBackScreen: () -> Unit,
    initPerson: PaymentPerson? = null,
    initAddress: PaymentAddress? = null,
    onContinue: (person: PaymentPerson, address: String) -> Unit
) {
    val firstName = remember { mutableStateOf(initPerson?.firstName.orEmpty()) }
    val lastName = remember { mutableStateOf(initPerson?.lastName.orEmpty()) }
    val phone = remember { mutableStateOf(initPerson?.phone.orEmpty()) }

    val address = remember {
        mutableStateOf(
            listOf(
                initAddress?.street.orEmpty(),
                initAddress?.house.orEmpty(),
                initAddress?.apartment.orEmpty()
            )
                .joinToString(", ")
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()

    ){
        TopMenu("Ваши данные", goToBackScreen = goToBackScreen)
        Column(
            modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserInfoField(
                name = stringResource(R.string.lastname),
                value = lastName.value
            ) {
                lastName.value = it
            }

            UserInfoField(
                name = stringResource(R.string.name),
                value = firstName.value
            ) {
                firstName.value = it
            }

            UserInfoField(
                name = stringResource(R.string.phone),
                value = phone.value
            ) {
                phone.value = it
            }

            UserInfoField(
                name = stringResource(R.string.city_hint),
                value = address.value
            ) {
                address.value = it
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onContinue(
                    PaymentPerson(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        phone = phone.value
                    ),
                    address.value
                ) },
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
}

