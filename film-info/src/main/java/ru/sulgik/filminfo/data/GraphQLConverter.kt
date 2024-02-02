package ru.sulgik.filminfo.data

import filmlist.FindFilmByIdQuery
import ru.sulgik.core.images.ImageURLFormatter
import ru.sulgik.filminfo.domain.entity.Film
import type.Profession
import type.Rating

class GraphQLConverter(
    private val imageURLFormatter: ImageURLFormatter
) {

    fun convert(film: FindFilmByIdQuery.Film): Film {
        return Film(
            id = film.id,
            title = film.name,
            subtitle = film.originalName,
            userRating = Film.UserRating(
                imdb = film.userRatings.imdb.toFloat(),
                kinopoisk = film.userRatings.kinopoisk.toFloat()
            ),
            genres = film.genres,
            countryName = film.country?.name,
            imageUrl = imageURLFormatter.format(film.img),
            description = film.description, ageRating = film.ageRating.convert(),
            runtime = film.runtime.toInt(),
            directors = film.directors.map { it.convert() },
            actors = film.actors.map { it.convert() },
            releaseDate = film.releaseDate,
        )
    }

    private fun FindFilmByIdQuery.Actor.convert(): Film.Actor {
        return Film.Actor(
            fullName = fullName, id = id, professions = professions.map { it.convert() }
        )
    }

    private fun FindFilmByIdQuery.Director.convert(): Film.Director {
        return Film.Director(
            fullName = fullName, id = id, professions = professions.map { it.convert() }
        )
    }

    private fun Profession.convert(): Film.Profession {
        return when (this) {
            Profession.ACTOR -> Film.Profession.ACTOR
            Profession.DIRECTOR -> Film.Profession.DIRECTOR
            Profession.UNKNOWN__ -> Film.Profession.UNKNOWN
        }
    }

    private fun Rating.convert(): Film.AgeRating {
        return when (this) {
            Rating.G -> Film.AgeRating.G
            Rating.NC17 -> Film.AgeRating.NC17
            Rating.PG -> Film.AgeRating.PG
            Rating.PG13 -> Film.AgeRating.PG13
            Rating.R -> Film.AgeRating.R
            Rating.UNKNOWN__ -> Film.AgeRating.UNKNOWN
        }
    }

}