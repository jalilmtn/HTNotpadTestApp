package com.example.htnotpadtestapp.presentation.notes

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.htnotpadtestapp.domain.model.Note

@Composable
fun NotesRoute(
    notesViewModel: NotesViewModel = hiltViewModel(),
    onNoteClick: (Note) -> Unit
) {
    val state = notesViewModel.state.value

    NoteScreen(state, onNoteClick, notesViewModel::prevDay, notesViewModel::nextDay)
}




