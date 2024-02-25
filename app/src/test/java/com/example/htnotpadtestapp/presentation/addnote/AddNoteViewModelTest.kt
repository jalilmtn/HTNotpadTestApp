package com.example.htnotpadtestapp.presentation.addnote

import androidx.lifecycle.SavedStateHandle
import com.example.htnotpadtestapp.data.repo.FakeAlarmScheduler
import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.usecases.AddAlarmUseCase
import com.example.htnotpadtestapp.domain.usecases.AddNoteUseCase
import com.example.htnotpadtestapp.domain.usecases.GetNoteUseCase
import com.example.htnotpadtestapp.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddNoteViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private lateinit var noteRepo: FakeNoteRepo
    private lateinit var addNotesUseCase: AddNoteUseCase
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var addAlarmUseCase: AddAlarmUseCase


    @Before
    fun setUp() {
        noteRepo = FakeNoteRepo()
        addAlarmUseCase = AddAlarmUseCase(FakeAlarmScheduler())

        addNotesUseCase = AddNoteUseCase(noteRepo, addAlarmUseCase)
        getNoteUseCase = GetNoteUseCase(noteRepo)
    }

    @Test
    fun nullIdShouldTrowException(): Unit = runBlocking {
        try {
            val viewModel = provideViewModel(null)
            viewModel
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(IllegalStateException::class.java)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun provideViewModel(id: Int?): AddNoteViewModel = AddNoteViewModel(
        savedStateHandle = SavedStateHandle(
            mapOf(
                ARG_NOTE_ID to id,
            )
        ),
        addNotesUseCase,
        getNoteUseCase,
        UnconfinedTestDispatcher(),
        UnconfinedTestDispatcher(),
    )


}