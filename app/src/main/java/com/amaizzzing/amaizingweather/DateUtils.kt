package com.amaizzzing.amaizingweather

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun millisToDateString(dateInMillis: Long) =
        SimpleDateFormat("dd.MM").format(Date(dateInMillis))

    fun millisToTimeString(dateInMillis: Long) =
        SimpleDateFormat("HH:ss").format(Date(dateInMillis))

    fun atEndOfDay(date: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.time = Date(date)
        calendar[Calendar.HOUR_OF_DAY] = 23
        calendar[Calendar.MINUTE] = 59
        calendar[Calendar.SECOND] = 59
        calendar[Calendar.MILLISECOND] = 999
        return calendar.time.time
    }

    fun atStartOfDay(date: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.time = Date(date)
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.time.time
    }
}