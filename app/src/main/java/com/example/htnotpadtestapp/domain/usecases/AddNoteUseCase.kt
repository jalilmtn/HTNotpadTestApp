package com.example.htnotpadtestapp.domain.usecases

import com.example.htnotpadtestapp.common.NoteError
import com.example.htnotpadtestapp.common.Resource
import com.example.htnotpadtestapp.common.localToUTC
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.model.toAlarmData
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler
import com.example.htnotpadtestapp.domain.repo.NoteRepo
import java.time.Instant
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepo: NoteRepo,
    private val alarmScheduler: AlarmScheduler
) {
    suspend operator fun invoke(note: Note): Resource<String?> {
        return when {
            note.title.isEmpty() -> Resource.Error(NoteError.EMPTY_TITLE)
            note.content.isEmpty() -> Resource.Error(NoteError.EMPTY_CONTENT)
            else -> {
                noteRepo.addNote(note)
                val utcTime = localToUTC(note.aTime + note.aDate)
                if (utcTime > localToUTC(Instant.now().epochSecond * 1000))
                    alarmScheduler.setAlarm(note.toAlarmData().copy(dateTime = utcTime))
                Resource.Success(null)
            }
        }
    }
}

