package ru.geekbrains.filmsapp.model.repository

import ru.geekbrains.filmsapp.model.data.Movie

interface RepositoryLocal {

    fun getFavouritesFromDb(): List<Movie>
    fun addMovieToFavourites(movie: Movie)
    fun addMovieToFavourites(movies: List<Movie>)
}