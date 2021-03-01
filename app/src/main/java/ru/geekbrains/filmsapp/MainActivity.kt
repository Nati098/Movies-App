package ru.geekbrains.filmsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        bindView()

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_movies, R.id.navigation_profile))
    }

    private fun bindView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    createAlertDialog(R.string.bottom_nav_item_home)
                    true
                }
                R.id.navigation_movies -> {
                    createAlertDialog(R.string.bottom_nav_item_movies)
                    true
                }
                R.id.navigation_profile -> {
                    createAlertDialog(R.string.bottom_nav_item_profile)
                    true
                }
            } }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                createAlertDialog(R.string.action_settings)
                true
            }
            R.id.action_search -> {
                createAlertDialog(R.string.action_search)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    private fun createAlertDialog(stringId: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(stringId)
            .setMessage(stringId)
//                .setIcon(drawableId)
            .setCancelable(true)
            .setPositiveButton(R.string.button_ok, { dialog, id -> })
        val alert = builder.create()
        alert.show()
    }
}

