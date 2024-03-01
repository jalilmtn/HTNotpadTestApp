package com.example.htnotpadtestapp.presentation.addnote

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.common.transparentBackAndIndicator
import com.example.htnotpadtestapp.presentation.addnote.components.AddNoteTopAppBar
import com.example.htnotpadtestapp.presentation.addnote.components.AlarmBottomSheetRow
import com.example.htnotpadtestapp.presentation.addnote.components.EditView
import com.example.htnotpadtestapp.presentation.addnote.components.NoteDetailTitleBar
import com.example.htnotpadtestapp.presentation.components.LargeSpacer
import com.example.htnotpadtestapp.presentation.theme.Grey88
import com.example.htnotpadtestapp.presentation.theme.textColor2
import com.example.htnotpadtestapp.presentation.theme.textColor5
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
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
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
                    showTimePickerDialog.value = true
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
                    saveNote.invoke()
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
                AlarmBottomSheetRow(
                    iconId = R.drawable.ic_clock,
                    title = "Later Today",
                    followingText = "6:30 PM"
                )
                HorizontalDivider(thickness = 1.5.dp, color = Grey88)
                AlarmBottomSheetRow(
                    iconId = R.drawable.ic_clock,
                    title = "Tomorrow Morning",
                    followingText = "6:30 PM"
                )
                HorizontalDivider(thickness = 1.5.dp, color = Grey88)
                AlarmBottomSheetRow(
                    iconId = R.drawable.ic_home,
                    title = "Home",
                    followingText = "Tehran"
                )
                HorizontalDivider(thickness = 1.5.dp, color = Grey88)
                AlarmBottomSheetRow(
                    modifier = Modifier.clickable {
                        showDatePickerDialog.value = true
                    },
                    iconId = R.drawable.ic_calendar_transparent,
                    title = "Pick a Date",
                    followingIconId = if (state.value.uiDate.isEmpty()) R.drawable.ic_plus else null,
                    followingText = if (state.value.uiDate.isNotEmpty()) state.value.uiDate + " " + state.value.uiTime else null
                )
            }
        },
        scaffoldState = scaffoldState,
        containerColor = Color.Transparent,
        sheetContainerColor = Color.White,
        sheetPeekHeight = 0.dp,
        topBar = {
            AddNoteTopAppBar(onBackClick = onBackClick, onSetDate = {
                coroutineScope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            })
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }) {
        Column(
            Modifier
                .padding(top = 16.dp)
                .fillMaxSize(),
        ) {
            NoteDetailTitleBar(Modifier.fillMaxWidth())
            LargeSpacer()
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (state.value.saved) Color.Transparent else MaterialTheme.colorScheme.onSecondary
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
                    interactionSource = interactionSource,
                    onValueChange = { title -> changeTitle.invoke(title) },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    singleLine = true,
                    colors = TextFieldDefaults.colors().transparentBackAndIndicator().copy(
                        focusedTextColor = MaterialTheme.colorScheme.textColor2
                    ),
                    placeholder = {
                        Text(
                            text = stringResource(R.string.note_title_lable),
                            color = MaterialTheme.colorScheme.textColor2.copy(alpha = .7f),
                            style = MaterialTheme.typography.headlineSmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                )
                //TODO: Create custom textField from BasicTextField to handle paddings properly
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    value = state.value.content,
                    interactionSource = interactionSource,
                    colors = TextFieldDefaults.colors().transparentBackAndIndicator().copy(
                        focusedTextColor = MaterialTheme.colorScheme.textColor5
                    ),
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.textColor5,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        lineHeight = 18.72.sp,
                    ),
                    onValueChange = { content -> changeContent.invoke(content) },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.note_content_lable),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 13.sp,
                                lineHeight = 18.72.sp,
                            ),
                            color = MaterialTheme.colorScheme.textColor5.copy(alpha = .7f),
                            fontStyle = FontStyle.Italic
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isFocused && state.value.saved.not())
                    EditView(onDoneClick = saveNote)
            }
        }
    }
}










