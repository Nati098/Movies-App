package ru.geekbrains.filmsapp.model

import ru.geekbrains.filmsapp.model.data.*

fun convertAccountDtoToModel(accountDTO: AccountDTO): Account {
    val data: FactDTO = accountDTO.fact!!
    return Account(data.id!!, data.name!!, data.username, data.includeAdult!!)
}

fun convertFavouritesDtoToModel(favDTO: FavouritesDTO): List<Movie> {
    val data: List<Movie> = favDTO.results!!
    return data.toCollection(mutableListOf())
}