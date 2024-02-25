package com.example.htnotpadtestapp.data.repo

import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler

class FakeAlarmScheduler : AlarmScheduler {
    override fun setAlarm(note: Note) {
    }

    override fun cancel(note: Note) {
    }
}