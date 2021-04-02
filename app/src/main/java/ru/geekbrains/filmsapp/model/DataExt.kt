package ru.geekbrains.filmsapp.model

import ru.geekbrains.filmsapp.model.data.*
import ru.geekbrains.filmsapp.model.room.MovieEntity

fun convertAccountDtoToModel(accountDTO: AccountDTO): Account {
    val data: FactDTO = accountDTO.fact!!
    return Account(data.id!!, data.name!!, data.username, data.includeAdult!!)
}

fun convertFavouritesDtoToModel(favDTO: FavouritesDTO): List<Movie> {
    val data: List<Movie> = favDTO.results!!
    return data.toCollection(mutableListOf())
}

fun convertFavouritesEntityToMoviesList(fav: List<MovieEntity>) : List<Movie> =
    fav.map {
        Movie(it.id, listOf(), it.title, it.overview, it.posterPath, it.releaseDate, it.popularity, it.adult)
    }

fun convertMovieToEntity(movie: Movie) : MovieEntity =
    MovieEntity(movie.id, movie.title, movie.overview, movie.posterPath, movie.releaseDate, movie.popularity, movie.adult)