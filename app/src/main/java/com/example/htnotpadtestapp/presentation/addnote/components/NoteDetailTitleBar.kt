package com.example.htnotpadtestapp.presentation.addnote.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.CircleIcon
import com.example.htnotpadtestapp.presentation.components.ProfileView
import com.example.htnotpadtestapp.presentation.components.SmallestSpacer

@Composable
fun NoteDetailTitleBar(modifier: Modifier) {
    Row(
        modifier
            .clip(
                MaterialTheme.shapes.large,
            ),
        Arrangement.SpaceBetween,
        Alignment.CenterVertically
    ) {
        Card(
            Modifier
                .clip(
                    shape = MaterialTheme.shapes.extraLarge,
                )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                Alignment.CenterVertically
            ) {
                CircleIcon(iconId = R.drawable.ic_cat_work, iconSize = 40.dp)
                Text(
                    text = stringResource(id = R.string.work_chip_label),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                SmallestSpacer()
            }
        }
        ProfileView(
            avatars = listOf(R.drawable.user1, R.drawable.user2),
            paddingUnit = 16.dp,
            avatarHeight = 32.dp
        )
    }
}