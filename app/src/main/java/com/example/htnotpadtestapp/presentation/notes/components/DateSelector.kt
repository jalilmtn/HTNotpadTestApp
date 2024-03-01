package com.example.htnotpadtestapp.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.SmallestSpacer
import com.example.htnotpadtestapp.presentation.notes.State
import com.example.htnotpadtestapp.presentation.theme.DarkBlue40
import com.example.htnotpadtestapp.presentation.theme.textColor3

@Composable
fun DateSelector(state: State, title: String, onPrev: () -> Unit, onNext: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPrev) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        Column(Modifier.padding(horizontal = 32.dp)) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                    tint = DarkBlue40
                )
                SmallestSpacer()
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                )
            }

            Text(
                text = state.dateTime.date,
                color = MaterialTheme.colorScheme.textColor3,
                style = MaterialTheme.typography.bodySmall
            )
        }
        IconButton(onClick = onNext) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                tint = Color.Unspecified,
                contentDescription = null
            )
        }

    }
}