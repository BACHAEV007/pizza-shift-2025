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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.pizza_shift_2025.domain.entity.Pizza

@Composable
fun DetailScreenComponent(
    body: Pizza,
    onItemClicked: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier
            .size(24.dp)
            .fillMaxWidth())
        val painter = rememberImagePainter(body.img)
        Image(
            painter = painter,
            contentDescription = "Pizza Image",
            modifier = Modifier
                .size(220.dp)
                .aspectRatio(1f)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(32.dp))
        DetailDescription(body)
        Spacer(modifier = Modifier.size(24.dp))
        LabelRow()
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(R.string.add_like_taste),
            fontSize = 16.sp,
            lineHeight = 24.sp,
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.size(24.dp))
        IngredientsGrid(body.toppings)
    }
}

@Composable
fun DetailDescription(body: Pizza){
    Column {
        Text(
            text = body.name,
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = 24.sp,
            style = MaterialTheme.typography.labelLarge,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "30 см, традиционное тесто",
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
fun LabelRow() {
    var activeIndex by remember { mutableStateOf(0) }
    val buttonLabels = listOf(
        stringResource(R.string.little),
        stringResource(R.string.medium),
        stringResource(R.string.large)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF3F4F6), shape = RoundedCornerShape(14.dp))
    ) {
        repeat(3) { index ->
            LabelButton(
                text = buttonLabels[index],
                isActive = index == activeIndex,
                modifier = Modifier.weight(1f),
                onClick = {
                    activeIndex = if (activeIndex == index) -1 else index
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