package ru.geekbrains.filmsapp.model.response

import java.lang.Error

data class ErrorResponse(override val statusCode: Int,
                         val statusMessage: String? = "",
                         val success: Boolean? = isSuccessStatusCode(statusCode)) : BaseResponse<Error>