package ru.sulgik.filmlist.domain.converter

import ru.sulgik.core.images.ImageURLFormatter
import ru.sulgik.filmlist.data.RemoteFilm
import ru.sulgik.filmlist.domain.entity.Film

class RemoteFilmConverter(
    private val imageURLFormatter: ImageURLFormatter,
) {

    fun convert(remoteFilms: List<RemoteFilm>): List<Film> {
        return remoteFilms.map { convert(it) }
    }

    fun convert(remoteFilm: RemoteFilm): Film {
        return Film(
            title = remoteFilm.title,
            subtitle = remoteFilm.subtitle,
            userRating = Film.UserRating(
                remoteFilm.userRating.imdb,
                remoteFilm.userRating.kinopoisk,
            ),
            genres = remoteFilm.genres,
            countryName = remoteFilm.countryName,
            imageUrl = imageURLFormatter.format(remoteFilm.imageUrl)
        )
    }

}

