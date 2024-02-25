package com.example.htnotpadtestapp.core

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.htnotpadtestapp.common.Constants.NOTIFICATION_CHANNEL_ID
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){

    @SuppressLint("ServiceCast")
    override fun onCreate() {
        super.onCreate()

        val channel =  NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
             "Note channel",
             NotificationManager.IMPORTANCE_HIGH
         )
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}