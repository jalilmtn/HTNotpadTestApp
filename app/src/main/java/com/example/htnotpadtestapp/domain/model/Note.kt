package com.example.htnotpadtestapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Note(
    val title: String,
    val content: String,
    val aTime: Long = 0,
    val aDate: Long = 0,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

fun Note.toAlarmData() = AlarmData(
    this.id,
    this.title,
    this.content,
    this.aDate + this.aTime
)


