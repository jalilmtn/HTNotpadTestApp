package com.example.htnotpadtestapp.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.CircleText
import com.example.htnotpadtestapp.presentation.components.HTFilterChip

@Composable
fun Categories(modifier: Modifier = Modifier, noteCount: Int = 0) {
    Card(
        modifier
            .clip(
                shape = MaterialTheme.shapes.extraLarge,
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            HTFilterChip(
                selected = true,
                onSelectedChange = {

                },
                label = {
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //TODO: change textColor based on selected
                        Text(
                            text = stringResource(R.string.all_chip_label),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        CircleText(text = noteCount.toString())
                    }
                }
            )
            HTFilterChip(
                selected = false,
                onSelectedChange = {

                },
                label = {
                    Text(
                        text = stringResource(R.string.work_chip_label),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            )
            HTFilterChip(
                selected = false,
                onSelectedChange = {

                },
                label = {
                    Text(
                        text = stringResource(R.string.life_style_chip_label),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            )
        }
    }
}