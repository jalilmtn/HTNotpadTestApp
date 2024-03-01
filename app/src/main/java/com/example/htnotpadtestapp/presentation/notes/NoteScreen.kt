package com.example.htnotpadtestapp.presentation.notes


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.presentation.components.SmallestSpacer
import com.example.htnotpadtestapp.presentation.notes.components.Categories
import com.example.htnotpadtestapp.presentation.notes.components.DateSelector
import com.example.htnotpadtestapp.presentation.notes.components.NoteItem
import com.example.htnotpadtestapp.presentation.notes.components.NotesItemsActionBar
import com.example.htnotpadtestapp.presentation.notes.components.NotesTopAppBar

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
            DateSelector(state,
                stringResource(R.string.today_title), onNext = onNext, onPrev = onPrev)
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
