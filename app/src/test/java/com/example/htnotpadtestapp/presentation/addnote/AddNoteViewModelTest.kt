package com.example.htnotpadtestapp.presentation.addnote

import androidx.lifecycle.SavedStateHandle
import com.example.htnotpadtestapp.data.repo.FakeAlarmScheduler
import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.usecases.AddNoteUseCase
import com.example.htnotpadtestapp.domain.usecases.GetNoteUseCase
import com.example.htnotpadtestapp.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
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

    @Before
    fun setUp() {
        noteRepo = FakeNoteRepo()
        addNotesUseCase = AddNoteUseCase(noteRepo, FakeAlarmScheduler())
        getNoteUseCase = GetNoteUseCase(noteRepo)
    }

    @Test
    fun nullIdShouldTrowException()= runBlocking {
        try {
            val viewModel = provideViewModel(null)
            viewModel
        }catch (e:Exception){
            assertThat(e).isInstanceOf(IllegalStateException::class.java)
        }
    }






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