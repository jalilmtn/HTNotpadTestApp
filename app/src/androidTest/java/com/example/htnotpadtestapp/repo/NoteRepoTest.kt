package com.example.htnotpadtestapp.repo

import androidx.test.filters.SmallTest
import com.example.htnotpadtestapp.data.source.NoteDB
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@HiltAndroidTest
@SmallTest
class NoteRepoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: NoteDB

    @Inject
    @Named("test_repo")
    lateinit var noteRepo: NoteRepo

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAndAddNote() = runBlocking {
        val note = Note("title", content = "content", id = 1)
        noteRepo.addNote(note)
        assertThat(noteRepo.getNote(1)).isEqualTo(note)
    }

    @Test
    fun getNotes(): Unit = runBlocking {
        val noteList = arrayListOf<Note>()
        (1..10).forEach {
            val note = Note("title $it", content = "content $it", id = it)
            noteList.add(note)
            noteRepo.addNote(note)
        }

        val notes = noteRepo.getNotes().first()
        assertThat(notes).containsExactlyElementsIn(noteList)
    }


    @Test
    fun updateNote() = runBlocking {
        val note = Note("title", content = "content", id = 1)
        noteRepo.addNote(note)
        val note2 = Note("title2", content = "content2", id = 1)
        noteRepo.addNote(note2)

        assertThat(noteRepo.getNote(1)).isEqualTo(note2)
    }
}