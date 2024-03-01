package com.example.htnotpadtestapp.presentation.notes

import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.GetNotesUseCase
import com.example.htnotpadtestapp.utils.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private lateinit var noteRepo: FakeNoteRepo
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var getNotesUseCase: GetNotesUseCase

    @Before
    fun setUp() {
        noteRepo = FakeNoteRepo()
        getNotesUseCase = GetNotesUseCase(noteRepo)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun startStateShouldBeEmptyList() = runBlocking {
        notesViewModel = NotesViewModel(
            getNotesUseCase,
            UnconfinedTestDispatcher(),
            UnconfinedTestDispatcher()
        )
        assertThat(notesViewModel.state.value.notes).isEqualTo(emptyList<Note>())
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getNotesMustChangeTheState() = runBlocking {
        val note = Note("title", "content", id = 1)
        noteRepo.addNote(note)

        notesViewModel = NotesViewModel(
            getNotesUseCase,
            UnconfinedTestDispatcher(),
            UnconfinedTestDispatcher()
        )

        assertThat(notesViewModel.state.value).isEqualTo(State(listOf(note)))
    }
}