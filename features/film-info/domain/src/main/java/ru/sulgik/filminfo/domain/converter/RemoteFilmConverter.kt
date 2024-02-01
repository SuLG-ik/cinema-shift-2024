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
            imageUrl = imageURLFormatter.format(remoteFilm.imageUrl),
            id = remoteFilm.id,
            ageRating = remoteFilm.ageRating.convert(),
            runtime = remoteFilm.runtime,
            directors = remoteFilm.directors.map { it.convert() },
            actors = remoteFilm.actors.map { it.convert() },
            releaseDate = remoteFilm.releaseDate
        )
    }

}

private fun RemoteFilm.Actor.convert(): Film.Actor {
    return Film.Actor(
        fullName = fullName, id = id, professions = professions.mapNotNull { it.convert() }
    )
}

private fun RemoteFilm.Director.convert(): Film.Director {
    return Film.Director(
        fullName = fullName, id = id, professions = professions.mapNotNull { it.convert() }
    )
}

private fun RemoteFilm.Profession.convert(): Film.Profession? {
    return when (this) {
        RemoteFilm.Profession.ACTOR -> Film.Profession.ACTOR
        RemoteFilm.Profession.DIRECTOR -> Film.Profession.DIRECTOR
        RemoteFilm.Profession.UNKNOWN -> null
    }
}

private fun RemoteFilm.AgeRating.convert(): Film.AgeRating {
    return when (this) {
        RemoteFilm.AgeRating.G -> Film.AgeRating.G
        RemoteFilm.AgeRating.NC17 -> Film.AgeRating.NC17
        RemoteFilm.AgeRating.PG -> Film.AgeRating.PG
        RemoteFilm.AgeRating.PG13 -> Film.AgeRating.PG13
        RemoteFilm.AgeRating.R -> Film.AgeRating.R
        RemoteFilm.AgeRating.UNKNOWN -> Film.AgeRating.UNKNOWN
    }
}

