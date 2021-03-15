package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Trend (val page: Int = 0,
                  val movies: List<Movie> = ArrayList(),
                  val totalPages: Int = 0,
                  val totalResults: Int = 0
) : Parcelable


fun getLocalTrending() : List<Trend> =
    listOf(
        Trend(
            1,
            listOf(
                Movie(1, listOf(1,2,3), "title1", "overview1", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title2", "overview2", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title3", "overview3", null, Date(), 1.8, false)),
            3,15831
        ),
        Trend(
            2,
            listOf(
                Movie(1, listOf(1,2,3), "title11", "overview111", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title22", "overview222", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title33", "overview333", null, Date(), 1.8, false)),
            3,15831
        ),
        Trend(
            3,
            listOf(
                Movie(1, listOf(1,2,3), "title111", "overview111", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title222", "overview233", null, Date(), 1.8, false),
                Movie(1, listOf(1,2,3), "title333", "overview333", null, Date(), 1.8, false)),
            3,15831
        )
    )