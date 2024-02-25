package com.example.htnotpadtestapp.presentation.addnote

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.htnotpadtestapp.data.repo.FakeAlarmScheduler
import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.model.Note
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
            provideViewModel(null)
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(IllegalStateException::class.java)
        }
    }

    @Test
    fun correctStateOnEditNote() = runBlocking {
        val note = Note(
            "title",
            "content",
            id = 1,
            aDate = 1708861000000,
            aTime = (2 * 60 + 30) * 60 * 1000
        )
        noteRepo.addNote(note)
        val viewModel = provideViewModel(1)
        assertThat(viewModel.state.value.id).isEqualTo(1)
        assertThat(viewModel.state.value.title).isEqualTo("title")
        assertThat(viewModel.state.value.content).isEqualTo("content")
        assertThat(viewModel.state.value.uiTime).isEqualTo("02:30")
        assertThat(viewModel.state.value.uiDate).isEqualTo("25-02-2024")
    }

    @Test
    fun changeTitle() = runBlocking {
        val note = Note("title", "content", id = 1, aDate = 100, aTime = 100)
        noteRepo.addNote(note)
        val viewModel = provideViewModel(1)
        viewModel.changeTitle("ok")
        assertThat(viewModel.state.value.title).isEqualTo("ok")
    }

    @Test
    fun changeContent() = runBlocking {
        val note = Note("title", "content", id = 1, aDate = 100, aTime = 100)
        noteRepo.addNote(note)
        val viewModel = provideViewModel(1)
        viewModel.changeContent("ok")
        assertThat(viewModel.state.value.content).isEqualTo("ok")
    }

    @Test
    fun changeTime() = runBlocking {
        val note = Note("title", "content", id = 1, aDate = 100, aTime = 100)
        noteRepo.addNote(note)
        val viewModel = provideViewModel(1)
        viewModel.changeTime(2, 30)
        assertThat(viewModel.state.value.time).isEqualTo((2 * 60 + 30) * 60 * 1000)
        assertThat(viewModel.state.value.uiTime).isEqualTo("02:30")
    }

    @Test
    fun changeDate() = runBlocking {
        val note = Note("title", "content", id = 1, aDate = 100, aTime = 0)
        noteRepo.addNote(note)
        val viewModel = provideViewModel(1)
        viewModel.changeDate(1708861955380)
        assertThat(viewModel.state.value.date).isEqualTo(1708861955380)
        assertThat(viewModel.state.value.uiDate).isEqualTo("2024-02-25")
    }

    @Test
    fun addNoteSuccess() = runBlocking {
        val viewModel = provideViewModel(-1)
        viewModel.state.value = State("test", "test")
        viewModel.signal.test {
            viewModel.saveNote()
            assertThat(awaitItem()).isInstanceOf(AddNoteViewModel.Signal.SuccessSave::class.java)
        }
    }@Test
    fun addNoteFail() = runBlocking {
        val viewModel = provideViewModel(-1)
        viewModel.state.value = State("", "test")
        viewModel.signal.test {
            viewModel.saveNote()
            assertThat(awaitItem()).isInstanceOf(AddNoteViewModel.Signal.SnackBarMessage::class.java)
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