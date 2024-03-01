package com.example.htnotpadtestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.htnotpadtestapp.presentation.addnote.addNoteScreen
import com.example.htnotpadtestapp.presentation.addnote.navigateToAddNote
import com.example.htnotpadtestapp.presentation.notes.ROUTE_NOTES
import com.example.htnotpadtestapp.presentation.notes.notesScreen

@Composable
fun HTNavHost(modifier :Modifier , navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = ROUTE_NOTES) {
        notesScreen(onNoteClick = {
            navController.navigateToAddNote(it.id)
        })
        addNoteScreen(navController::navigateUp)
    }
}