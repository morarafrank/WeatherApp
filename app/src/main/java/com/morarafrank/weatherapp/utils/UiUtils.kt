package com.morarafrank.weatherapp.utils
import java.text.SimpleDateFormat
import java.util.*

object UiUtils {



    fun formatLastUpdated(dt: Long): String {
        val date = Date(dt * 1000)

        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        dateFormat.timeZone = timeZone
        timeFormat.timeZone = timeZone

        val dateStr = dateFormat.format(date)
        val timeStr = timeFormat.format(date)

        return "Last updated on $dateStr at $timeStr"
    }

}