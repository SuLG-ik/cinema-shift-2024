package ru.sulgik.filminfo.data

import filmlist.FindFilmByIdQuery

fun FindFilmByIdQuery.Film.convert(): RemoteFilm {
    return RemoteFilm(
        title = name, subtitle = originalName,
        userRating = RemoteFilm.UserRating(
            imdb = userRatings.imdb.toFloat(),
            kinopoisk = userRatings.kinopoisk.toFloat()
        ),
        genres = genres,
        countryName = country?.name,
        description = description,
        imageUrl = img,
    )
}
