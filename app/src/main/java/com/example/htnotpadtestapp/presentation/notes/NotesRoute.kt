package com.example.htnotpadtestapp.presentation.notes

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.htnotpadtestapp.domain.model.Note

@Composable
fun NotesRoute(
    notesViewModel: NotesViewModel = hiltViewModel(),
    onAddNote: () -> Unit,
    onNoteClick: (Note) -> Unit
) {
    val state = notesViewModel.state.value

    NoteScreen(state, onAddNote, onNoteClick)
}

@Composable
fun NoteScreen(state: State, onAddNote: () -> Unit, onNoteClick: (Note) -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = onAddNote) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }) {
        LazyColumn(modifier = Modifier.padding(it), content = {
            items(state.notes) { note ->
                NoteItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(
                            shape = MaterialTheme.shapes.medium,
                        )
                        .clickable { onNoteClick.invoke(note) },
                    note
                )
            }
        })
    }
}

@Composable
private fun NoteSpacer(modifier: Modifier = Modifier, color: Color) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        modifier
            .height(1.dp)
    ) {

        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}

@Composable
private fun NoteItem(modifier: Modifier = Modifier, note: Note) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            NoteSpacer(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
