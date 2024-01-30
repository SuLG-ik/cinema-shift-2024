package ru.sulgik.filmlist.domain.converter

import ru.sulgik.filmlist.data.RemoteFilm
import ru.sulgik.filmlist.domain.entity.Film

fun List<RemoteFilm>.convert(): List<Film> {
    return map { it.convert() }
}

fun RemoteFilm.convert(): Film {
    return Film(
        title = title,
        subtitle = subtitle,
        userRating = Film.UserRating(
            userRating.imdb,
            userRating.kinopoisk,
        ),
        genres = genres,
        countryName = countryName,
        imageUrl = imageUrl
    )
}
