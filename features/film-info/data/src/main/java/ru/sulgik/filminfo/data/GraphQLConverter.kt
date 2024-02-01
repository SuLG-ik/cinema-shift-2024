package ru.sulgik.filminfo.data

import filmlist.FindFilmByIdQuery
import type.Profession
import type.Rating

fun FindFilmByIdQuery.Film.convert(): RemoteFilm {
    return RemoteFilm(
        id = id,
        title = name,
        subtitle = originalName,
        userRating = RemoteFilm.UserRating(
            imdb = userRatings.imdb.toFloat(),
            kinopoisk = userRatings.kinopoisk.toFloat()
        ),
        genres = genres,
        countryName = country?.name,
        description = description,
        imageUrl = img, ageRating = ageRating.convert(),
        runtime = runtime.toInt(),
        directors = directors.map { it.convert() },
        actors = actors.map { it.convert() }
    )
}

private fun FindFilmByIdQuery.Actor.convert(): RemoteFilm.Actor {
    return RemoteFilm.Actor(
        fullName = fullName, id = id, professions = professions.map { it.convert() }
    )
}

private fun FindFilmByIdQuery.Director.convert(): RemoteFilm.Director {
    return RemoteFilm.Director(
        fullName = fullName, id = id, professions = professions.map { it.convert() }
    )
}

private fun Profession.convert(): RemoteFilm.Profession {
    return when (this) {
        Profession.ACTOR -> RemoteFilm.Profession.ACTOR
        Profession.DIRECTOR -> RemoteFilm.Profession.DIRECTOR
        Profession.UNKNOWN__ -> RemoteFilm.Profession.UNKNOWN
    }
}

private fun Rating.convert(): RemoteFilm.AgeRating {
    return when (this) {
        Rating.G -> RemoteFilm.AgeRating.G
        Rating.NC17 -> RemoteFilm.AgeRating.NC17
        Rating.PG -> RemoteFilm.AgeRating.PG
        Rating.PG13 -> RemoteFilm.AgeRating.PG13
        Rating.R -> RemoteFilm.AgeRating.R
        Rating.UNKNOWN__ -> RemoteFilm.AgeRating.UNKNOWN
    }
}
