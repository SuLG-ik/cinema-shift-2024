package ru.sulgik.filmlist.data.converter

import filmlist.LoadFilmListQuery
import ru.sulgik.core.images.ImageURLFormatter
import ru.sulgik.filmlist.domain.entity.Film

class RemoteFilmConverter(
    private val imageURLFormatter: ImageURLFormatter,
) {

    fun convert(remoteFilms: List<LoadFilmListQuery.Film>): List<Film> {
        return remoteFilms.map { convert(it) }
    }

    fun convert(remoteFilm: LoadFilmListQuery.Film): Film {
        return Film(
            id = remoteFilm.id,
            title = remoteFilm.name,
            subtitle = remoteFilm.originalName,
            description = remoteFilm.description,
            userRating = Film.UserRating(
                remoteFilm.userRatings.imdb.toFloat(),
                remoteFilm.userRatings.kinopoisk.toFloat(),
            ),
            genres = remoteFilm.genres,
            countryName = remoteFilm.country?.name,
            imageUrl = imageURLFormatter.format(remoteFilm.img),
            releaseDate = remoteFilm.releaseDate,
        )
    }

}

