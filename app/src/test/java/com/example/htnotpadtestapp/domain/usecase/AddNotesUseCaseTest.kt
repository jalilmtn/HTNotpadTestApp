package com.example.htnotpadtestapp.domain.usecase

import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.data.repo.FakeAlarmScheduler
import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.AddNoteUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddNotesUseCaseTest {
    private lateinit var addNoteUseCase: AddNoteUseCase
    private lateinit var fakeNoteRepo: FakeNoteRepo
    private lateinit var fakeAlarmScheduler: FakeAlarmScheduler

    @Before
    fun setUp() {
        fakeNoteRepo = FakeNoteRepo()
        fakeAlarmScheduler = FakeAlarmScheduler()
        addNoteUseCase = AddNoteUseCase(fakeNoteRepo, fakeAlarmScheduler)
    }

    @Test
    fun emptyTitleMustReturnError() = runBlocking {
        val result = addNoteUseCase.invoke(Note("", "content"))
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.noteError?.name).isEqualTo(NoteError.EMPTY_TITLE.name)
    }

    @Test
    fun emptyContentMustReturnError() = runBlocking {
        val result = addNoteUseCase.invoke(Note("title", ""))
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.noteError?.name).isEqualTo(NoteError.EMPTY_CONTENT.name)
    }

    @Test
    fun noteWithTitleAndContentMustReturnSuccess() = runBlocking {
        val note = Note("title", "content")
        val result = addNoteUseCase.invoke(note)
        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).isEqualTo(null)
        assertThat(result.noteError).isEqualTo(null)
    }


}