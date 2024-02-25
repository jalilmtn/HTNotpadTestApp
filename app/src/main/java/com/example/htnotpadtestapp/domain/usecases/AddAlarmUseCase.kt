package com.example.htnotpadtestapp.domain.usecases

import com.example.htnotpadtestapp.common.localToUTC
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.model.toAlarmData
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler
import java.time.Instant
import javax.inject.Inject

class AddAlarmUseCase @Inject constructor(
    private val alarmScheduler: AlarmScheduler
) {
    operator fun invoke(note: Note) {
        val utcTime = localToUTC(note.aTime + note.aDate)
        if (utcTime > localToUTC(Instant.now().epochSecond * 1000))
            alarmScheduler.setAlarm(note.toAlarmData().copy(dateTime = utcTime))
    }
}

