package ru.geekbrains.filmsapp.ui

import android.app.Application
import androidx.room.Room
import ru.geekbrains.filmsapp.model.room.FavouritesDao
import ru.geekbrains.filmsapp.model.room.FavouritesDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private var instance: App? = null
        private var db: FavouritesDatabase? = null
        private const val DB_NAME = "Favourites.db"

        fun getContext() = instance!!.applicationContext

        fun getFavouritesDao(): FavouritesDao {
            if (db == null) {
                synchronized(FavouritesDatabase::class.java) {
                    if (db == null) {
                        if (instance == null) {
                            throw IllegalStateException("Application is null while creating DataBase")
                        }
                        db = Room.databaseBuilder(
                            instance!!.applicationContext,
                            FavouritesDatabase::class.java,
                            DB_NAME
                        ).allowMainThreadQueries().build()
                    }
                }
            }

            return db!!.getDao()
        }

    }

}