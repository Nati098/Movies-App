package ru.geekbrains.filmsapp.viewmodel.viewstate

import ru.geekbrains.filmsapp.model.data.Account

class ProfileViewState(val account: Account? = null, error: Throwable? = null) : BaseViewState<Account?> (account, error)
