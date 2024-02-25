package com.example.htnotpadtestapp.data.repo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_MUTABLE
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import com.example.htnotpadtestapp.common.Constants.ALARM_MESSAGE
import com.example.htnotpadtestapp.common.Constants.ALARM_TITLE
import com.example.htnotpadtestapp.domain.model.AlarmData
import com.example.htnotpadtestapp.domain.repo.AlarmScheduler
import javax.inject.Inject

class AlarmSchedulerImpl @Inject constructor(
    private val context: Context,
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @SuppressLint("MissingPermission")
    override fun setAlarm(alarmData: AlarmData) {
        val intent = Intent(context, AlarmTriggerReceiverImpl::class.java)
        intent.apply {
            putExtra(ALARM_TITLE, alarmData.title)
            putExtra(ALARM_MESSAGE, alarmData.content)
        }
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmData.dateTime,
            PendingIntent.getBroadcast(
                context,
                alarmData.id.hashCode(),
                intent,
                FLAG_UPDATE_CURRENT or FLAG_MUTABLE
            )
        )
    }

    //TODO: delete alarm here
    override fun cancel(alarmData: AlarmData) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmData.id.hashCode(),
                Intent(context, AlarmTriggerReceiverImpl::class.java),
                FLAG_UPDATE_CURRENT or FLAG_MUTABLE
            )
        )
    }
}