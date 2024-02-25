package com.example.htnotpadtestapp.presentation.addnote

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.htnotpadtestapp.R
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteRoute(addNoteViewModel: AddNoteViewModel = hiltViewModel(), navigateBack: () -> Unit) {

    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = Unit, block = {
        addNoteViewModel.signal.collectLatest {
            when (it) {
                is AddNoteViewModel.Signal.SnackBarMessage -> snackBarHostState.showSnackbar(message = it.message)
                AddNoteViewModel.Signal.SuccessSave -> navigateBack.invoke()
            }
        }
    })
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    val showDatePickerDialog = rememberSaveable { mutableStateOf(false) }
    val showTimePickerDialog = rememberSaveable { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(is24Hour = true)

    if (showDatePickerDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePickerDialog.value = false
                    addNoteViewModel.changeDate(
                        datePickerState.selectedDateMillis
                    )
                }) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerDialog.value = false }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    if (showTimePickerDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showTimePickerDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showTimePickerDialog.value = false
                    addNoteViewModel.changeTime(
                        timePickerState.hour,
                        timePickerState.minute
                    )
                }
                ) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePickerDialog.value = false }) {
                    Text("Cancel")
                }
            }
        ) {
            TimePicker(
                modifier = Modifier.fillMaxWidth(),
                state = timePickerState,
                layoutType = TimePickerLayoutType.Vertical
            )
        }
    }

    Scaffold(snackbarHost = {
        SnackbarHost(snackBarHostState)
    }) {
        Column(
            Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = addNoteViewModel.state.value.title,
                onValueChange = { title -> addNoteViewModel.changeTitle(title) },
                label = { Text(text = stringResource(R.string.note_title_lable)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 160.dp),
                value = addNoteViewModel.state.value.content,
                onValueChange = { content -> addNoteViewModel.changeContent(content) },
                label = {
                    Text(text = stringResource(R.string.note_content_lable))
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "")
                TextButton(
                    content = { Text(text = addNoteViewModel.state.value.uiDate) },
                    onClick = {
                        showDatePickerDialog.value = true
                    })
                TextButton(
                    content = { Text(text = addNoteViewModel.state.value.uiTime) },
                    onClick = {
                        showTimePickerDialog.value = true
                    })
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = addNoteViewModel::saveNote
            ) {
                Text(text = stringResource(R.string.save_note_button_text))
            }
        }
    }
}