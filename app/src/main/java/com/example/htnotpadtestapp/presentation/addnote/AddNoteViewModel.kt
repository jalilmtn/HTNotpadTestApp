package com.example.htnotpadtestapp.presentation.addnote

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.htnotpadtestapp.common.Constants.NORMAL_DATE_FORMAT
import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.common.getDate
import com.example.htnotpadtestapp.common.getTime
import com.example.htnotpadtestapp.di.IoDispatcher
import com.example.htnotpadtestapp.di.MainDispatcher
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.AddNoteUseCase
import com.example.htnotpadtestapp.domain.usecases.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneId
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val noteId: Int = checkNotNull(savedStateHandle[ARG_NOTE_ID])

    var state = mutableStateOf(State())
        private set

    private val _signal = MutableSharedFlow<Signal>()
    val signal = _signal.asSharedFlow()

    init {
        if (noteId != -1) {
            getNote(noteId)
        }
    }

    private fun getNote(id: Int) {
        viewModelScope.launch(ioDispatcher) {
            val result = getNoteUseCase(id)
            withContext(mainDispatcher) {
                when (result) {
                    is Resource.Error -> _signal.emit(
                        Signal.SnackBarMessage(
                            result.noteError?.message ?: NoteError.GENERAL.message
                        )
                    )

                    is Resource.Success -> {
                        result.data?.let {
                            state.value =
                                if (result.data.aDate != 0L)
                                    State(
                                        result.data.title,
                                        result.data.content,
                                        id = result.data.id,
                                        date = result.data.aDate,
                                        time = result.data.aTime,
                                        uiDate = getDate(result.data.aDate, NORMAL_DATE_FORMAT),
                                        uiTime = getTime(result.data.aTime)
                                    )
                                else
                                    State(
                                        result.data.title,
                                        result.data.content,
                                        id = result.data.id
                                    )
                        }
                    }
                }
            }
        }
    }

    fun changeTitle(title: String) {
        state.value = state.value.copy(title = title, saved = false)
    }

    fun changeDate(date: Long?) {
        date?.let {
            state.value = state.value.copy(
                date = date,
                uiDate = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
                    .toString()
            )
        }
    }

    fun changeTime(hour: Int, min: Int) {
        val time = TimeUnit.HOURS.toMillis(hour.toLong()) + TimeUnit.MINUTES.toMillis(
            min.toLong()
        )
        state.value = state.value.copy(time = time, uiTime = getTime(time))
    }

    fun changeContent(content: String) {
        state.value = state.value.copy(content = content, saved = false)
    }

    fun saveNote() {
        viewModelScope.launch(ioDispatcher) {
            val result =
                addNoteUseCase(
                    Note(
                        state.value.title,
                        state.value.content,
                        id = state.value.id,
                        aDate = state.value.date,
                        aTime = state.value.time
                    )
                )
            withContext(mainDispatcher) {
                when (result) {
                    is Resource.Error -> _signal.emit(
                        Signal.SnackBarMessage(
                            result.noteError?.message ?: NoteError.GENERAL.message
                        )
                    )

                    is Resource.Success -> {
                        _signal.emit(Signal.SuccessSave)
                        state.value = state.value.copy(
                            saved = true
                        )
                    }
                }
            }
        }
    }


    sealed class Signal {
        data class SnackBarMessage(val message: String) : Signal()
        data object SuccessSave : Signal()
    }
}

data class State(
    val title: String = "",
    val content: String = "",
    val uiDate: String = "",
    val uiTime: String = "",
    val date: Long = 0,
    val time: Long = 0,
    val id: Int? = null,
    val saved: Boolean = false
)
