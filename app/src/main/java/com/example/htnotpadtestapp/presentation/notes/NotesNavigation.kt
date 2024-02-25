package com.example.htnotpadtestapp.presentation.notes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.htnotpadtestapp.domain.model.Note

const val ROUTE_NOTES = "notes_route"

fun NavGraphBuilder.notesScreen(onAddNote: () -> Unit, onNoteClick: (Note) -> Unit) {
    composable(
        route = ROUTE_NOTES
    ) {
        NotesRoute(onAddNote = onAddNote, onNoteClick = onNoteClick)
    }
}