package com.example.htnotpadtestapp.presentation.addnote.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.CircleIconButton
import com.example.htnotpadtestapp.presentation.components.HTAppBar

@Composable
fun AddNoteTopAppBar(onBackClick: () -> Unit) {
    HTAppBar(
        title = {},
        navigationIcon = {
            CircleIconButton(onclick = onBackClick, iconId = R.drawable.ic_back, iconSize = 48.dp)
        },
        actions = {
            CircleIconButton(
                onclick = { /*TODO*/ },
                iconId = R.drawable.ic_direct_inbox,
                iconSize = 48.dp
            )
            CircleIconButton(
                modifier = Modifier.padding(start = 12.dp),
                onclick = { /*TODO*/ },
                iconId = R.drawable.ic_pin,
                iconSize = 48.dp
            )
        },
    )
}