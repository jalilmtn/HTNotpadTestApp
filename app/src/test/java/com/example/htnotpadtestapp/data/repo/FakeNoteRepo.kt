package com.example.htnotpadtestapp.data.repo

import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepo : NoteRepo {
    private val notes = mutableListOf<Note>()

    override fun getNotes(): Flow<List<Note>> {
        return flow { emit(notes) }
    }

    override suspend fun addNote(note: Note) {
        notes.add(note)
    }

    override suspend fun getNote(id: Int): Note? {
        return notes.find { it.id == id }
    }
}