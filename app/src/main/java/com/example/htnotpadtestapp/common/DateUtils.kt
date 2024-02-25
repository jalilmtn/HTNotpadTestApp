package com.example.htnotpadtestapp.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
fun localToUTC(time: Long): Long {
    try {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        val date = Date(time)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val strDate: String = dateFormat.format(date)
        val dateFormatLocal = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
        val utcDate: Date? = dateFormatLocal.parse(strDate)
        return utcDate!!.time
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return 0L
}

@SuppressLint("SimpleDateFormat")
fun getDate(milliSeconds: Long, dateFormat: String?): String {
    val formatter = SimpleDateFormat(dateFormat)
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

@SuppressLint("SimpleDateFormat")
fun getTime(millis: Long): String {
    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toHours(millis),
        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.MILLISECONDS.toHours(millis) * 60)
}