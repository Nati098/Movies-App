package ru.geekbrains.filmsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.model.SystemPreferences
import ru.geekbrains.filmsapp.model.SystemPreferences.ACCOUNT_DATA
import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.accountFromJson
import ru.geekbrains.filmsapp.model.data.accountToJson

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        bindView()
    }

    private fun bindView() {
        val switchAdult = findViewById<SwitchCompat>(R.id.switch_adult)
        var account = SystemPreferences.getStringPreference(ACCOUNT_DATA)?.let { accountFromJson(it) } ?: Account()

        switchAdult.isChecked = account.includeAdult
        switchAdult.setOnCheckedChangeListener { _, isChecked ->
            account.includeAdult = isChecked
            SystemPreferences.setPreference(ACCOUNT_DATA, accountToJson(account))
        }
    }

}