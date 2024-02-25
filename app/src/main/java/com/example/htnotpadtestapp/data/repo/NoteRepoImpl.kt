package com.example.htnotpadtestapp.data.repo

import com.example.htnotpadtestapp.data.source.NoteDao
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import kotlinx.coroutines.flow.Flow

class NoteRepoImpl(private val noteDao: NoteDao):NoteRepo {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    override suspend fun getNote(id: Int): Note? {
        return noteDao.getNoteById(id)
    }

}