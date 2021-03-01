package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
data class Trend (val page: Int = 0,
                  val movies: List<Movie> = ArrayList(),
                  val totalPages: Int = 0,
                  val totalResults: Int = 0
) : Parcelable