package com.example.htnotpadtestapp.domain.repo

import com.example.htnotpadtestapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepo {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun getNote(id: Int): Note?
}