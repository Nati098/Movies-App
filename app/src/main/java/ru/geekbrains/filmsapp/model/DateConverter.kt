package ru.geekbrains.filmsapp.model

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? = date?.let { date.time }
}