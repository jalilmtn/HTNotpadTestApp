package com.example.htnotpadtestapp.domain.repo

import com.example.htnotpadtestapp.domain.model.Note

interface MyNotificationManager {
    fun showNotification(note: Note)
}