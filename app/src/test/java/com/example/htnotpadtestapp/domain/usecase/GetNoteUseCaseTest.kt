package com.example.htnotpadtestapp.domain.usecase

import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.GetNoteUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNoteUseCaseTest {
    private lateinit var getNoteUseCase: GetNoteUseCase
    private lateinit var fakeNoteRepo: FakeNoteRepo

    @Before
    fun setUp() {
        fakeNoteRepo = FakeNoteRepo()
        getNoteUseCase = GetNoteUseCase(fakeNoteRepo)
    }

    @Test
    fun wrongIdMustReturnError() = runBlocking {
        fakeNoteRepo.addNote(Note(content = "test", title = "test"))
        val result = getNoteUseCase.invoke(1)
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.noteError?.name).isEqualTo(NoteError.NOTE_NOT_FOUND.name)
    }

    @Test
    fun correctIdMustReturnSuccess() = runBlocking {
        val note = Note(content = "test", title = "test", id = 1)
        fakeNoteRepo.addNote(note)
        val result = getNoteUseCase.invoke(1)
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).isEqualTo(note)
    }


}