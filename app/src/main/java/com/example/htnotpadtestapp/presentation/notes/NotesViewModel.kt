package com.example.htnotpadtestapp.presentation.notes

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.htnotpadtestapp.di.IoDispatcher
import com.example.htnotpadtestapp.di.MainDispatcher
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    val notes: List<Note> = emptyList()
)