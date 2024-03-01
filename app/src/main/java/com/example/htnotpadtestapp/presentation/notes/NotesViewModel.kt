package com.example.htnotpadtestapp.presentation.notes

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.htnotpadtestapp.common.toHTFormat
import com.example.htnotpadtestapp.di.IoDispatcher
import com.example.htnotpadtestapp.di.MainDispatcher
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {
    var state = mutableStateOf(State())
        private set

    init {
        getNotes()
    }

    fun nextDay(){
        val newDate = state.value.dateTime.localDateTime.plusDays(1)
        state.value = state.value.copy(dateTime = DateTime(newDate.toHTFormat(), newDate))
    }

    fun prevDay(){
        val newDate = state.value.dateTime.localDateTime.minusDays(1)
        state.value = state.value.copy(dateTime = DateTime(newDate.toHTFormat(), newDate))
    }
    private fun getNotes() {
        viewModelScope.launch(ioDispatcher) {
            getNotesUseCase().collectLatest {
                withContext(mainDispatcher) {
                    state.value = State(it)
                }
            }
        }
    }
}


@Stable
data class State(
    val notes: List<Note> = emptyList(),
    val dateTime: DateTime = DateTime()
)

data class DateTime(
    val date: String = LocalDateTime.now().toHTFormat(),
    val localDateTime: LocalDateTime = LocalDateTime.now()
)