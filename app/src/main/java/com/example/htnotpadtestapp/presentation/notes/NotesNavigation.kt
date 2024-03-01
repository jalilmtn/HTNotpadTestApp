package com.example.htnotpadtestapp.presentation.notes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.htnotpadtestapp.domain.model.Note

const val ROUTE_NOTES = "notes_route"

fun NavGraphBuilder.notesScreen( onNoteClick: (Note) -> Unit) {
    composable(
        route = ROUTE_NOTES
    ) {
        NotesRoute( onNoteClick = onNoteClick)
    }
}