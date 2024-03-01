package com.example.htnotpadtestapp.presentation.addnote.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.presentation.theme.Grey88

@Composable
fun EditView(onDoneClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Grey88.copy(alpha = 0f),
                            Grey88,
                            Grey88.copy(alpha = 0f),
                        )
                    )
                ),
            thickness = 1.5.dp,
            color = Color.Transparent
        )
        FontSettingView()
        EditTabView(onDoneClick)
    }
}
