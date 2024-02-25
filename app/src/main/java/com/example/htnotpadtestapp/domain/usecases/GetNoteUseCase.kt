package com.example.htnotpadtestapp.domain.usecases

import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import javax.inject.Inject


class GetNoteUseCase @Inject constructor(private val noteRepo: NoteRepo) {
    suspend operator fun invoke(id: Int): Resource<Note> {
        return when(val note = noteRepo.getNote(id)){
            null ->  Resource.Error(NoteError.NOTE_NOT_FOUND)
            else -> Resource.Success(note)
        }
    }
}