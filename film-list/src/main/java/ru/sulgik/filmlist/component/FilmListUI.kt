package ru.sulgik.filmlist.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.filmlist.ui.Film
import ru.sulgik.filmlist.ui.FilmListScreen
import ru.sulgik.filmlist.domain.entity.Film as FilmEntity

@Composable
fun FilmListUI(
    component: FilmList,
    modifier: Modifier = Modifier,
) {
    val state = component.state.subscribeAsState()
    FilmListScreen(
        films = state.value.filmList.toUI(),
        onFilmAbout = { component.onFilmAbout(it.id) },
        modifier = modifier,
    )
}

private fun List<FilmEntity>.toUI(): List<Film> {
    return map { it.toUI() }
}

private fun FilmEntity.toUI(): Film {
    return Film(
        id = id,
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
        releaseDate = releaseDate,
    )
}
