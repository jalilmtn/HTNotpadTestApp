package com.example.htnotpadtestapp.data.repo

import com.example.htnotpadtestapp.domain.model.AlarmData
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler

class FakeAlarmScheduler : AlarmScheduler {
    private val alarms = mutableListOf<AlarmData>()
    override fun setAlarm(alarmData: AlarmData) {
        alarms.add(alarmData)
    }

    override fun cancel(alarmData: AlarmData) {
        alarms.remove(alarmData)
    }

    fun getAlarms():MutableList<AlarmData>{
        return alarms
    }
}