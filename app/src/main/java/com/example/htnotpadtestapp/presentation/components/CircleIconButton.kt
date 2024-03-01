package com.example.htnotpadtestapp.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.presentation.theme.Grey87


@Composable
fun CircleIconButton(
    modifier: Modifier = Modifier,
    onclick: () -> Unit, @DrawableRes iconId: Int, iconSize: Dp,
    backColor: Color = Color.Unspecified,
    borderColor: Color = Grey87,
    iconPadding:Dp = 12.dp
) {
    IconButton(
        modifier = modifier
            .size(iconSize)
            .clip(CircleShape)
            .background(color = backColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = CircleShape
            ), onClick = onclick
    ) {
        Icon(
            modifier = Modifier.padding(iconPadding),
            painter = painterResource(id = iconId),
            contentDescription = "",)
    }
}