package ru.geekbrains.filmsapp.model.repository

import ru.geekbrains.filmsapp.model.convertFavouritesEntityToMoviesList
import ru.geekbrains.filmsapp.model.convertMovieToEntity
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.room.FavouritesDao

class RepositoryLocalImpl(private val dao: FavouritesDao) : RepositoryLocal {
    override fun getFavouritesFromDb(): List<Movie> =
        convertFavouritesEntityToMoviesList(dao.allMovies())


    override fun addMovieToFavourites(movie: Movie) {
        dao.insertMovie(convertMovieToEntity(movie))
    }

    override fun addMovieToFavourites(movies: List<Movie>) {
        dao.insertAllMovies(*movies.map { convertMovieToEntity(it) }.toTypedArray())
    }
}