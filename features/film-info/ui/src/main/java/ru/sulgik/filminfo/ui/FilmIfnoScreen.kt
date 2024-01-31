package ru.sulgik.filminfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.uikit.film.FilmDetails

data class Film(
    val title: String,
    val subtitle: String,
    val userRating: UserRating,
    val genres: List<String>,
    val countryName: String?,
    val imageUrl: String,
) {
    data class UserRating(
        val imdb: Float,
        val kinopoisk: Float,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmScreen(
    film: Film?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "О фильме")
                },
            )
        },
        modifier = modifier,
    ) {
        if (film != null) FilmInfo(
            film = film, modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun FilmInfo(
    film: Film,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        FilmDetails(
            title = film.title,
            subtitle = film.subtitle,
            image = film.imageUrl,
            ratingImdb = film.userRating.imdb,
            ratingKinopoisk = film.userRating.imdb,
        )
    }
}