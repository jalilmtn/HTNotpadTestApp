package com.example.htnotpadtestapp.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

@Composable
fun CircleText(
    modifier: Modifier = Modifier,
    text: String = "0",
) {
    val color = MaterialTheme.colorScheme.secondary
    Text(
        modifier = modifier
            .drawBehind {
                drawCircle(
                    color = color,
                    radius = this.size.minDimension + this.size.minDimension / 2
                )
            },
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = Color.White
    )
}