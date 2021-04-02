package ru.geekbrains.filmsapp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.geekbrains.filmsapp.model.DateConverter

@Database(entities = arrayOf(MovieEntity::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun getDao() : FavouritesDao
}
