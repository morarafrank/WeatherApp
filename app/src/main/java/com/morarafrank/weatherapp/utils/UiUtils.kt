package com.morarafrank.weatherapp.utils
import android.os.Build
import androidx.annotation.RequiresApi
import com.morarafrank.weatherapp.domain.model.ForecastItem
import java.text.SimpleDateFormat
import java.util.*
import java.time.*
import java.time.format.DateTimeFormatter
import kotlin.random.Random

object UiUtils {



    fun formatLastUpdated(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)

        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        val timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        dateFormat.timeZone = timeZone
        timeFormat.timeZone = timeZone

        val dateStr = dateFormat.format(date)
        val timeStr = timeFormat.format(date)

        return "Last updated on $dateStr at $timeStr"
    }
    fun formatDate(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)
        val dateFormat = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        return dateFormat.format(date)
    }

    fun formatTime(dt: Long?): String {
        val date = Date(dt?.times(1000) ?: 0L)
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        timeFormat.timeZone = TimeZone.getTimeZone("Africa/Nairobi")
        return timeFormat.format(date)
    }

    fun toCapitation(sentence: String): String {
        return sentence
            .split(" ")
            .joinToString(" ") { word -> word.replaceFirstChar { it.uppercaseChar() } }
    }


}