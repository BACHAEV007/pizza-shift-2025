package com.example.pizza_shift_2025.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.domain.entity.PaymentDebitCard
import com.example.pizza_shift_2025.domain.entity.PaymentPerson
import retrofit2.http.Header

@Composable
fun CardScreen(
    modifier: Modifier,
    onBack: () -> Unit,
    onContinue: (PaymentDebitCard) -> Unit
) {
    val pan = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        TopMenu (
            title = stringResource(R.string.checkout_second_screen),
            goToBackScreen = onBack
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            UserInfoField(
                name = stringResource(R.string.card_pan_hint),
                value = pan.value
            ) {
                pan.value = it
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                UserInfoField(
                    name = stringResource(R.string.card_date_hint),
                    value = date.value,
                    modifier = Modifier.weight(1f)
                ) {
                    date.value = it
                }

                UserInfoField(
                    name = stringResource(R.string.card_cvv_hint),
                    value = cvv.value,
                    modifier = Modifier.weight(1f)
                ) {
                    cvv.value = it
                }
            }
        }

        Button(
            onClick = {
                val card = PaymentDebitCard(pan.value, date.value, cvv.value)
                onContinue(card)
            },
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