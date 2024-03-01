package com.example.htnotpadtestapp.presentation.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.common.transparentBackAndIndicator
import com.example.htnotpadtestapp.presentation.addnote.components.AddNoteTopAppBar
import com.example.htnotpadtestapp.presentation.addnote.components.AlarmBottomSheetRow
import com.example.htnotpadtestapp.presentation.addnote.components.EditView
import com.example.htnotpadtestapp.presentation.addnote.components.NoteDetailTitleBar
import com.example.htnotpadtestapp.presentation.components.LargeSpacer
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    changeDate: (date: Long?) -> Unit,
    changeTime: (Int, Int) -> Unit,
    snackBarHostState: SnackbarHostState,
    state: MutableState<State>,
    saveNote: () -> Unit,
    changeContent: (String) -> Unit,
    changeTitle: (String) -> Unit,
    onBackClick: () -> Unit,
) {
    val needSaving = remember {
        mutableStateOf(false)
    }
    val scaffoldState =
        rememberBottomSheetScaffoldState(
            bottomSheetState = rememberStandardBottomSheetState(
                initialValue = SheetValue.Hidden,
                skipHiddenState = false
            )
        )
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    val showDatePickerDialog = rememberSaveable { mutableStateOf(false) }
    val showTimePickerDialog = rememberSaveable { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(is24Hour = true)
    val coroutineScope = rememberCoroutineScope()

    if (showDatePickerDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePickerDialog.value = false
                    changeDate.invoke(
                        datePickerState.selectedDateMillis
                    )
                }) {
                    Text(stringResource(R.string.dialog_confirm_text))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerDialog.value = false }) {
                    Text(stringResource(R.string.dialog_cancel_text))
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
                    changeTime.invoke(
                        timePickerState.hour,
                        timePickerState.minute
                    )
                }
                ) {
                    Text(stringResource(R.string.dialog_confirm_text))
                }
            },
            dismissButton = {
                TextButton(onClick = { showTimePickerDialog.value = false }) {
                    Text(stringResource(R.string.dialog_cancel_text))
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

    BottomSheetScaffold(
        sheetContent = {
            Column(
                Modifier.padding(start = 24.dp, bottom = 32.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AlarmBottomSheetRow(R.drawable.ic_clock, "Later Today", "6:30 PM")
                HorizontalDivider()
                AlarmBottomSheetRow(R.drawable.ic_clock, "Tomorrow Morning", "6:30 PM")
                HorizontalDivider()
                AlarmBottomSheetRow(R.drawable.ic_home, "Home", "Tehran")
                HorizontalDivider()
                AlarmBottomSheetRow(
                    R.drawable.ic_calendar_transparent,
                    "Pick a Deate",
                    followingIconId = R.drawable.ic_plus
                )
            }
        },
        scaffoldState = scaffoldState,
        containerColor = Color.Transparent,
        sheetContainerColor = Color.White,
        sheetPeekHeight = 0.dp,
        topBar = {
            AddNoteTopAppBar(onBackClick = onBackClick)
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            NoteDetailTitleBar(Modifier.fillMaxWidth())
            LargeSpacer()
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (scaffoldState.bottomSheetState.targetValue == SheetValue.Expanded) Color.Transparent else MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier.fillMaxSize(),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0),
                    bottomStart = CornerSize(0)
                ),
            ) {
                TextField(
                    modifier = Modifier
                        .background(
                            color = Color.Transparent
                        )
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    value = state.value.title,
                    onValueChange = { title -> changeTitle.invoke(title) },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    singleLine = true,
                    colors = TextFieldDefaults.colors().transparentBackAndIndicator(),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.note_title_lable),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.value.content,
                    colors = TextFieldDefaults.colors().transparentBackAndIndicator(),
                    onValueChange = { content -> changeContent.invoke(content) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.note_content_lable),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))

                EditView(onDoneClick = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                })
            }
        }
    }
}










