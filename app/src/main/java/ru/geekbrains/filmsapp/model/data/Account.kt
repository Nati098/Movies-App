package ru.geekbrains.filmsapp.model.data

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(val id: Int = 0,
                   val name: String = "",
                   val username: String? = null,
                   var includeAdult: Boolean = false
) : Parcelable {
    override fun toString(): String {
        return "Account(id=$id, name='$name', username=$username, includeAdult=$includeAdult)"
    }
}

fun accountFromJson(data: String?): Account? = data?.let { Gson().fromJson(it, Account::class.java) }

fun accountToJson(data: Account): String = Gson().toJson(data)
