package com.example.htnotpadtestapp.domain.usecases

import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNotesUseCase @Inject constructor(private val noteRepo: NoteRepo) {
    operator fun invoke(): Flow<List<Note>> {
        return noteRepo.getNotes()
    }
}