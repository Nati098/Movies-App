package ru.geekbrains.filmsapp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieEntity::class), version = 1, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun getDao() : FavouritesDao
}
