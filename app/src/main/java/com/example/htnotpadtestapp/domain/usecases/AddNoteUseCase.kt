package com.example.htnotpadtestapp.domain.usecases

import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepo: NoteRepo,
    private val addAlarmUseCase: AddAlarmUseCase
) {
    suspend operator fun invoke(note: Note): Resource<String?> {
        return when {
            note.title.isEmpty() -> Resource.Error(NoteError.EMPTY_TITLE)
            note.content.isEmpty() -> Resource.Error(NoteError.EMPTY_CONTENT)
            else -> {
                noteRepo.addNote(note)
                addAlarmUseCase(note)
                Resource.Success(null)
            }
        }
    }
}

