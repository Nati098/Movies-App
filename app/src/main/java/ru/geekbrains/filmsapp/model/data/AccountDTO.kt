package ru.geekbrains.filmsapp.model.data

data class AccountDTO (
    val fact: FactDTO?
)


data class FactDTO(
    val id: Int?,
    val name: String?,
    val username: String?,
    val includeAdult: Boolean?
)
