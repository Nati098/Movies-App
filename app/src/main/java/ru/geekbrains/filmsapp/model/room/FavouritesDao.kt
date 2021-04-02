package ru.geekbrains.filmsapp.model.room

import androidx.room.*

@Dao
interface FavouritesDao {

    @Query("SELECT * FROM MovieEntity")
    fun allMovies(): List<MovieEntity>

    @Query("SELECT COUNT() FROM MovieEntity")
    fun getCountMovies(): Long

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(entity: MovieEntity)

    @Insert
    fun insertAllMovies(vararg users: MovieEntity?)

    @Query("DELETE FROM MovieEntity WHERE id = :id")
    fun deleteMovieById(id: Int)
}
