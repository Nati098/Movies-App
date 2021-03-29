package ru.geekbrains.filmsapp.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: Date = Date(),
    val popularity: Double = 0.0,
    val adult: Boolean = false

)
