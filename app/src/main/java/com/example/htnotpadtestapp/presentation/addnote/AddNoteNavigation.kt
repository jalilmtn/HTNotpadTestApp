package com.example.htnotpadtestapp.presentation.addnote

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ARG_NOTE_ID = "noteId"
const val ROUTE_ADD_NOTE = "route_add_note"


fun NavController.navigateToAddNote(noteId: Int? = null) {
    navigate("$ROUTE_ADD_NOTE/${noteId ?: -1}")
}

fun NavGraphBuilder.addNoteScreen(navigateBack: () -> Unit) {

    composable(
        route = "$ROUTE_ADD_NOTE/{$ARG_NOTE_ID}",
        arguments = listOf(navArgument(ARG_NOTE_ID) {
            type = NavType.IntType
            defaultValue = -1
        })
    ) {
        AddNoteRoute(navigateBack = navigateBack)
    }
}


