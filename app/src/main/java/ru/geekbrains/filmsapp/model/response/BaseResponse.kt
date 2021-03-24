package ru.geekbrains.filmsapp.model.response

interface BaseResponse<T> {
    val statusCode: Int
}

fun isSuccessStatusCode(code: Int) =
    when(code) {
        1, 13, 21, 40 -> true
        else -> false
    }

