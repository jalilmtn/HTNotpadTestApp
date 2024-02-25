package com.example.htnotpadtestapp.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

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