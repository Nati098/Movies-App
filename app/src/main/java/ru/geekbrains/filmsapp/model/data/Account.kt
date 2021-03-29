package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(val id: Int = 0,
                 val name: String = "",
                 val username: String? = null,
                 val includeAdult: Boolean = false
) : Parcelable {
    override fun toString(): String {
        return "Account(id=$id, name='$name', username=$username, includeAdult=$includeAdult)"
    }
}