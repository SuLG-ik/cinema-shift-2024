package ru.sulgik.filmlist.data

import filmlist.LoadFilmListQuery

fun LoadFilmListQuery.Data.convert(): List<RemoteFilm> {
    return getCinemaToday.films.map {
        it.convert()
    }
}

fun LoadFilmListQuery.Film.convert(): RemoteFilm {
    return RemoteFilm(
        title = name, subtitle = name,
        userRating = RemoteFilm.UserRating(
            imdb = userRatings.imdb.toFloat(),
            kinopoisk = userRatings.kinopoisk.toFloat()
        ),
        genres = genres,
        countryName = country?.name,
        imageUrl = img,
    )
}