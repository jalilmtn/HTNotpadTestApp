package com.example.htnotpadtestapp.domain.usecase

import com.example.htnotpadtestapp.common.localToUTC
import com.example.htnotpadtestapp.data.repo.FakeAlarmScheduler
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.model.toAlarmData
import com.example.htnotpadtestapp.domain.usecases.AddAlarmUseCase
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class AddAlarmUseCaseTest {
    private lateinit var addAlarmUseCase: AddAlarmUseCase
    private lateinit var alarmScheduler: FakeAlarmScheduler

    @Before
    fun setUp() {
        alarmScheduler = FakeAlarmScheduler()
        addAlarmUseCase = AddAlarmUseCase(alarmScheduler)
    }


    @Test
    fun dateMustBeAfterNow(){
        val note = Note("test", "test", aDate = 100, aTime = 100)
        addAlarmUseCase.invoke(note)
        val utcTime = localToUTC(note.aTime + note.aDate)
        assertThat(alarmScheduler.getAlarms()).doesNotContain(note.toAlarmData().copy(dateTime = utcTime))
    }

    @Test
    fun setAlarm(){
        //Tue Feb 07 2192 01:34:37
        val note = Note("test", "test", aDate = 7008860077737, aTime = 0)
        addAlarmUseCase.invoke(note)
        val utcTime = localToUTC(note.aTime + note.aDate)
        assertThat(alarmScheduler.getAlarms()).contains(note.toAlarmData().copy(dateTime = utcTime))
    }

}