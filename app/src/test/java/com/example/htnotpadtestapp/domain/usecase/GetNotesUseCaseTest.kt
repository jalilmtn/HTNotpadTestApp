package com.example.htnotpadtestapp.domain.usecase

import com.example.htnotpadtestapp.data.repo.FakeNoteRepo
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.usecases.GetNotesUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {
    private lateinit var getNotesUseCase: GetNotesUseCase
    private lateinit var fakeNoteRepo: FakeNoteRepo

    @Before
    fun setUp() {
        fakeNoteRepo = FakeNoteRepo()
        getNotesUseCase = GetNotesUseCase(fakeNoteRepo)
    }

    @Test
    fun getNotes(): Unit = runBlocking {
        val notes  = mutableListOf<Note>()
        (1..10).forEach {
            val note = Note(content = "test $it", title = "test $it", id = it)
            notes.add(note)
            fakeNoteRepo.addNote(note)
        }

        assertThat(fakeNoteRepo.getNotes().first()).containsExactlyElementsIn(notes)
    }



}