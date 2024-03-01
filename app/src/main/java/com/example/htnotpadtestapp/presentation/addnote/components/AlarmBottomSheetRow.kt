package com.example.htnotpadtestapp.presentation.addnote.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.htnotpadtestapp.presentation.theme.Grey22

@Composable
fun AlarmBottomSheetRow(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    followingText: String? = null,
    @DrawableRes followingIconId: Int? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = iconId), contentDescription = null)
            VerticalDivider(
                modifier = Modifier.height(8.dp),
                thickness = 1.dp,
                color = Color.Black.copy(alpha = .5f)
            )
            Text(
                text = title, style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 18.sp
                )
            )
        }
        followingIconId?.let {
            Icon(painter = painterResource(id = followingIconId), contentDescription = null)

        }
        followingText?.let {
            Text(
                text = followingText, style = MaterialTheme.typography.bodySmall.copy(
                    lineHeight = 18.sp
                )
            )
        }
    }
}