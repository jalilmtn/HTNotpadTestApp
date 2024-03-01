package com.example.htnotpadtestapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.presentation.theme.Grey87


@Composable
fun CircleIcon(
    @DrawableRes iconId: Int, iconSize: Dp
) {
    Icon(
        modifier = Modifier
            .size(iconSize)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Grey87,
                shape = CircleShape
            ),
        painter = painterResource(id = iconId),
        tint = Color.Unspecified,
        contentDescription = "",
    )
}