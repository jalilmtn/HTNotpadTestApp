package com.example.htnotpadtestapp.domain.repo

import com.example.htnotpadtestapp.domain.model.AlarmData

interface AlarmScheduler {
    fun setAlarm(alarmData: AlarmData)
    fun cancel(alarmData: AlarmData)
}