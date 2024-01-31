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
        modifier = modifier,
    )
}

private fun FilmEntity.convert(): Film {
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
