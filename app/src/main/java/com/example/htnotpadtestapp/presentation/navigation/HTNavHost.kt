package com.example.htnotpadtestapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.htnotpadtestapp.presentation.addnote.addNoteScreen
import com.example.htnotpadtestapp.presentation.addnote.navigateToAddNote
import com.example.htnotpadtestapp.presentation.notes.ROUTE_NOTES
import com.example.htnotpadtestapp.presentation.notes.notesScreen

@Composable
fun HTNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = ROUTE_NOTES) {
        notesScreen(onAddNote = navController::navigateToAddNote, onNoteClick = {
            navController.navigateToAddNote(it.id)
        })
        addNoteScreen(navController::navigateUp)
    }
}