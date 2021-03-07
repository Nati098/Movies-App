package ru.geekbrains.filmsapp.model

import ru.geekbrains.filmsapp.model.data.Account
import ru.geekbrains.filmsapp.model.data.Genre
import ru.geekbrains.filmsapp.model.data.Movie
import ru.geekbrains.filmsapp.model.data.Trend

class RepositoryImpl : Repository {
    override fun getTrendingFromServer(): Trend {
//        TODO("Not yet implemented")
        return Trend()
    }

    override fun getGenresFromServer(): List<Genre> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMoviesListFromServer(): List<Movie> {
//        TODO("Not yet implemented")
        return ArrayList()
    }

    override fun getMovieDetailsFromServer(movieId: Int): Movie {
//        TODO("Not yet implemented")
        return Movie()
    }

    override fun getAccountFromServer(): Account {
//        TODO("Not yet implemented")
        return Account()
    }
}

/*
Ключ API (v3 auth): 99e8522ad678a5d83cf57ccf2a340d90
Пример API-запроса: https://api.themoviedb.org/3/movie/550?api_key=99e8522ad678a5d83cf57ccf2a340d90
Ключ доступа к API (v4 auth): eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWU4NTIyYWQ2NzhhNWQ4M2NmNTdjY2YyYTM0MGQ5MCIsInN1YiI6IjYwMzdlZmEwMTU5NTlmMDAzZjg3NzkzNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.rAoXf0TgCH9-GC9l1Gt5j8hZwfFZQI-TrzXgRLJWA3U
 */
