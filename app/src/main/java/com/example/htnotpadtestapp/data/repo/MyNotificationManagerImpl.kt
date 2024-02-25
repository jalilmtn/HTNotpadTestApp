package com.example.htnotpadtestapp.data.repo

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.common.Constants.NOTIFICATION_CHANNEL_ID
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.MyNotificationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@SuppressLint("ServiceCast")
class MyNotificationManagerImpl @Inject constructor(@ApplicationContext private val context: Context) :
    MyNotificationManager {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    override fun showNotification(note: Note) {
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentText(note.content)
            .setContentTitle(note.title)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        notificationManager.notify(note.id.hashCode(), notification)
    }
}
