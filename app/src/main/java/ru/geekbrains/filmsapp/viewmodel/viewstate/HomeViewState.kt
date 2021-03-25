package ru.geekbrains.filmsapp.viewmodel.viewstate

import ru.geekbrains.filmsapp.model.data.Trend

class HomeViewState(val trends: Trend? = null, val rated: Trend? = null, error: Throwable? = null) : BaseViewState<Trend?> (trends, error)
