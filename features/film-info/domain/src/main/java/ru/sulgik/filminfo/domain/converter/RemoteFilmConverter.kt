package ru.sulgik.filminfo.domain.converter

import ru.sulgik.core.images.ImageURLFormatter
import ru.sulgik.filminfo.data.RemoteFilm
import ru.sulgik.filminfo.domain.entity.Film

class RemoteFilmConverter(
    private val imageURLFormatter: ImageURLFormatter,
) {
    fun convert(remoteFilm: RemoteFilm): Film {
        return Film(
            title = remoteFilm.title,
            description = remoteFilm.description,
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

