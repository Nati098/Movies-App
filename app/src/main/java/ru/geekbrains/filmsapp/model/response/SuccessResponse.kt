package ru.geekbrains.filmsapp.model.response

data class SuccessResponse<T>(override val statusCode: Int = 200,
                              val page: Int,
                              val totalPages: Int,
                              val data: T?) : BaseResponse<T>