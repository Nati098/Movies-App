package ru.geekbrains.filmsapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import ru.geekbrains.filmsapp.R
import ru.geekbrains.filmsapp.ui.extension.addToBackStack
import ru.geekbrains.filmsapp.ui.extension.createCancelableAlertDialog
import ru.geekbrains.filmsapp.ui.fragment.FavouritesFragment
import ru.geekbrains.filmsapp.ui.fragment.HomeFragment
import ru.geekbrains.filmsapp.ui.fragment.ProfileFragment
import ru.geekbrains.filmsapp.viewmodel.ConnectionMonitor

class MainActivity : AppCompatActivity() {

    private lateinit var connMonitor : ConnectionMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        bindView()

        connMonitor = ConnectionMonitor(this)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_favourite,
            R.id.navigation_profile
        ))
    }

    private fun bindView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    supportFragmentManager.addToBackStack<HomeFragment>(this, Bundle())
                    true
                }
                R.id.navigation_favourite -> {
                    supportFragmentManager.addToBackStack<FavouritesFragment>(this, Bundle())
                    true
                }
                R.id.navigation_profile -> {
                    supportFragmentManager.addToBackStack<ProfileFragment>(this, Bundle())
                    true
                }
                else -> true
            } }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                createCancelableAlertDialog(R.string.action_settings)
                true
            }
            R.id.action_search -> {
                createCancelableAlertDialog(R.string.action_search)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
