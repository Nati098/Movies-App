package ru.geekbrains.filmsapp.model.data

data class FavouritesDTO(val page: Int?,
                         val totalPages: Int?,
                         val totalResults: Int?,
                         val results: List<Movie>?
)