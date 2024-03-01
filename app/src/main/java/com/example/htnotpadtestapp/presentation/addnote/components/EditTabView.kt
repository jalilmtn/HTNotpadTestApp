package com.example.htnotpadtestapp.presentation.addnote.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.CircleIconButton
import com.example.htnotpadtestapp.presentation.theme.Grey22

@Composable
fun EditTabView(onDoneClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 24.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            TextSettingTabView()
            CircleIconButton(
                onclick = onDoneClick,
                iconId = R.drawable.ic_tick_circle,
                iconSize = 42.dp,
                modifier = Modifier.padding(8.dp),
                backColor = Grey22,
                borderColor = Color.Transparent,
                iconPadding = 8.dp
            )
        }
    }
}