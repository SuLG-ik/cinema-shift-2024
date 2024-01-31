package ru.sulgik.filmlist.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import ru.sulgik.filmlist.ui.Film
import ru.sulgik.filmlist.ui.FilmListScreen
import ru.sulgik.filmlist.domain.entity.Film as FilmEntity

@Composable
fun FilmListUI(
    component: FilmList,
    modifier: Modifier = Modifier,
) {
    val state = component.state.collectAsState()
    FilmListScreen(
        films = state.value.filmList.convert(),
        onFilmAbout = { component.onFilmAbout(it.id) },
        modifier = modifier,
    )
}

private fun List<FilmEntity>.convert(): List<Film> {
    return map { it.convert() }
}

private fun FilmEntity.convert(): Film {
    return Film(
        id = id,
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
