package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class Movie(val id: Int = 0,
                 val genreIds: List<Int> = ArrayList(),
                 val title: String = "",
                 val overview: String? = null,
                 val posterPath: String? = null,
                 val releaseDate: Date = Date(),
                 val popularity: Double = 0.0,
                 val adult: Boolean = false
) : Parcelable