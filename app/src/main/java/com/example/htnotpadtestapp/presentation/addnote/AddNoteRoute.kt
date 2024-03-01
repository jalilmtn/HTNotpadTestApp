package com.example.htnotpadtestapp.presentation.addnote

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddNoteRoute(addNoteViewModel: AddNoteViewModel = hiltViewModel(), navigateBack: () -> Unit) {

    val snackBarHostState = remember { SnackbarHostState() }
    val state = addNoteViewModel.state
    LaunchedEffect(key1 = Unit, block = {
        addNoteViewModel.signal.collectLatest {
            when (it) {
                is AddNoteViewModel.Signal.SnackBarMessage -> snackBarHostState.showSnackbar(message = it.message)
                AddNoteViewModel.Signal.SuccessSave -> navigateBack.invoke()
            }
        }
    })

    AddNoteScreen(
        changeDate = addNoteViewModel::changeDate,
        changeTime =  addNoteViewModel::changeTime,
        snackBarHostState = snackBarHostState,
        state = state,
        saveNote = addNoteViewModel::saveNote,
        changeContent = addNoteViewModel::changeContent,
        changeTitle = addNoteViewModel::changeTitle,
        onBackClick = navigateBack
    )

}
