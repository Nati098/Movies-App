package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val id: Int,
                 val genres: Array<Genre>,
                 val title: String = "",
                 val overview: String? = null
) : Parcelable