package com.example.htnotpadtestapp.presentation.notes


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.presentation.notes.components.DateSelector
import com.example.htnotpadtestapp.presentation.components.HTAppBar
import com.example.htnotpadtestapp.presentation.notes.components.NoteItem
import com.example.htnotpadtestapp.presentation.components.ProfileView
import com.example.htnotpadtestapp.presentation.components.SmallestSpacer
import com.example.htnotpadtestapp.presentation.theme.Grey85

@Composable
fun NoteScreen(
    state: State,
    onNoteClick: (Note) -> Unit,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0),
        topBar = {
            NotesTopAppBar()

        }) {
        Column(Modifier.padding(top = it.calculateTopPadding() + 16.dp)) {
            DateSelector(state, "ToDay", onNext = onNext, onPrev = onPrev)
            SmallestSpacer()
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        SmallestSpacer()
                    }
                    item {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clip(
                                    MaterialTheme.shapes.large,
                                ),
                            Arrangement.SpaceBetween
                        ) {
                            Categories(noteCount = state.notes.count())
                            IconButton(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(color = Color.White),
                                onClick = { }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_pin),
                                    tint = Color.Black,
                                    contentDescription = ""
                                )
                            }
                        }

                    }
                    item {
                        NotesItemsActionBar()
                    }
                    items(state.notes) { note ->
                        NoteItem(
                            modifier = Modifier
                                .clip(
                                    shape = MaterialTheme.shapes.large,
                                )
                                .clickable { onNoteClick.invoke(note) },
                            note
                        )
                    }
                })
        }
    }
}


@Composable
private fun NotesTopAppBar() {
    HTAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Alireza Abbasi",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        navigationIcon = {
            ProfileView(
                avatars = listOf(R.drawable.user1),
                paddingUnit = 20.dp,
                avatarHeight = 40.dp
            )
        },
        actions = {
            IconButton(
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_status),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
            }
            IconButton(
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_direct_inbox),
                    tint = Color.Black,
                    contentDescription = ""
                )
            }
        },
    )
}

@Composable
private fun NotesItemsActionBar() {
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
        Text(text = "Recent All Notes", style = MaterialTheme.typography.bodyMedium)
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