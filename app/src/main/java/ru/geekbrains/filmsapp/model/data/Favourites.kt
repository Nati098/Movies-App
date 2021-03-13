package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favourites(val page: Int = 0,
                      val totalPages: Int = 0,
                      val totalResults: Int = 0,
                      val results: List<Movie> = ArrayList()
) : Parcelable