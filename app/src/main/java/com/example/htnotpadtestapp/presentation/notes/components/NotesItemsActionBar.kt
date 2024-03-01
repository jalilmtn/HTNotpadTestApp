package com.example.htnotpadtestapp.presentation.notes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.theme.Grey85
import com.example.htnotpadtestapp.presentation.theme.textColor2

@Composable
fun NotesItemsActionBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(
                MaterialTheme.shapes.large,
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        //TODO: should be changed based on category
        Text(
            text = "Recent All Notes",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.textColor2
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_row_vertical),
                    contentDescription = null
                )
            }
            VerticalDivider(color = Grey85, thickness = 1.dp, modifier = Modifier.height(20.dp))
            IconButton(onClick = {/*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_normal),
                    contentDescription = null
                )
            }
        }
    }
}