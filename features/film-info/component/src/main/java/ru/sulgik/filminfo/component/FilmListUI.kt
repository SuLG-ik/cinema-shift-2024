package ru.sulgik.filminfo.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.sulgik.filminfo.ui.Film
import ru.sulgik.filminfo.ui.FilmScreen
import ru.sulgik.filminfo.domain.entity.Film as FilmEntity

@Composable
fun FilmInfoUI(
    component: FilmInfo,
    modifier: Modifier = Modifier,
) {
    val state = component.state.collectAsState()
    FilmScreen(
        film = state.value.film?.convert(),
        onSchedule = component::onSchedule,
        onBack = component::onBack,
        modifier = modifier,
    )
}

private fun FilmEntity.convert(): Film {
    return Film(
        title = title,
        subtitle = subtitle,
        description = description,
        userRating = Film.UserRating(
            userRating.imdb,
            userRating.kinopoisk,
        ),
        genres = genres,
        countryName = countryName,
        imageUrl = imageUrl,
        id = id,
        ageRating = ageRating.convert(),
        runtime = runtime,
        directors = directors.map { it.convert() },
        releaseDate = releaseDate,
        actors = actors.map { it.convert() },
    )
}



private fun FilmEntity.Actor.convert(): Film.Actor {
    return Film.Actor(
        fullName = fullName, id = id, professions = professions.map { it.convert() }
    )
}

private fun FilmEntity.Director.convert(): Film.Director {
    return Film.Director(
        fullName = fullName, id = id, professions = professions.map { it.convert() }
    )
}

private fun FilmEntity.Profession.convert(): Film.Profession {
    return when (this) {
        FilmEntity.Profession.ACTOR -> Film.Profession.ACTOR
        FilmEntity.Profession.DIRECTOR -> Film.Profession.DIRECTOR
    }
}

private fun FilmEntity.AgeRating.convert(): Film.AgeRating {
    return when (this) {
        FilmEntity.AgeRating.G -> Film.AgeRating.G
        FilmEntity.AgeRating.NC17 -> Film.AgeRating.NC17
        FilmEntity.AgeRating.PG -> Film.AgeRating.PG
        FilmEntity.AgeRating.PG13 -> Film.AgeRating.PG13
        FilmEntity.AgeRating.R -> Film.AgeRating.R
        FilmEntity.AgeRating.UNKNOWN -> Film.AgeRating.UNKNOWN
    }
}

