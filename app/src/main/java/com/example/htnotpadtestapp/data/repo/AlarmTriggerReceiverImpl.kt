package com.example.htnotpadtestapp.data.repo

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.example.htnotpadtestapp.common.Constants.ALARM_MESSAGE
import com.example.htnotpadtestapp.common.Constants.ALARM_TITLE
import com.example.htnotpadtestapp.domain.model.Note
import com.example.htnotpadtestapp.domain.repo.MyNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmTriggerReceiverImpl :
    BroadcastReceiver() {

    @Inject
    lateinit var myNotificationManager: MyNotificationManager


    override fun onReceive(context: Context, intent: Intent?) {
        intent?.extras?.run {
            val title = this.getString(ALARM_TITLE)
            val message = this.getString(ALARM_MESSAGE)
            val isPermissionGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
            if (title != null && message != null && isPermissionGranted)
                myNotificationManager.showNotification(Note(title = title, content = message))

        }
    }
}