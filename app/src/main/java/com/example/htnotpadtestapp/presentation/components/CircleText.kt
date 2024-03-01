package com.example.htnotpadtestapp.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

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
                    radius = this.size.minDimension
                )
            },
        text = text,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.labelSmall,
        color = Color.White
    )
}