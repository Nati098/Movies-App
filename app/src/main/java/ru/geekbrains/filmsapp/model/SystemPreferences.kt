package ru.geekbrains.filmsapp.model

import android.content.Context
import android.content.SharedPreferences
import ru.geekbrains.filmsapp.ui.App

object SystemPreferences {
    private val SYSTEM_PREFERENCES = "system_preferences"
    val ACCOUNT_DATA = "show_adult"

    private fun getSP(): SharedPreferences {
        return App.getContext().getSharedPreferences(SYSTEM_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun getBooleanPreference(key: String?): Boolean {
        return getSP().getBoolean(key, false)
    }

    fun getStringPreference(key: String?): String? {
        return getSP().getString(key, "")
    }

    fun setPreference(key: String?, value: String?) {
        getSP().edit().putString(key, value).apply()
    }

    fun setPreference(key: String?, value: Boolean) {
        getSP().edit().putBoolean(key, value).apply()
    }
}