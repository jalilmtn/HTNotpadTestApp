package com.example.htnotpadtestapp.presentation.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.presentation.components.LargeSpacer
import com.example.htnotpadtestapp.presentation.components.MediumSpacer
import com.example.htnotpadtestapp.presentation.components.ProfileView
import com.example.htnotpadtestapp.presentation.theme.DarkBlue40

@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            MediumSpacer()
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            LargeSpacer()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileView(
                    modifier = Modifier.weight(1f),
                    avatars = listOf(R.drawable.user1, R.drawable.user2),
                    paddingUnit = 24.dp,
                    avatarHeight = 32.dp
                )

                IconButton(
                    onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_pin),
                        tint = Color.Black,
                        contentDescription = ""
                    )
                }
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .background(color = DarkBlue40),
                    onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_edit),
                        tint = Color.White,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}