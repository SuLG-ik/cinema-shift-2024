package ru.sulgik.filmlist.ui

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(locale = "ru")
@Composable
fun FilmItemPreview() {
    FilmItem(
        film = Film(
            title = "Title",
            subtitle = "subtitle",
            userRating = Film.UserRating(imdb = 4.5f, kinopoisk = 6.7f),
            genres = listOf("Драма"),
            countryName = null,
            imageUrl = "https://www.google.com/#q=vim",
        ),
        modifier = Modifier.width(240.dp)
    )
}

@Preview
@Composable
fun FilmListPreview() {
    FilmListScreen(
        films = listOf(
            Film(
                title = "integer",
                subtitle = "fabellas",
                userRating = Film.UserRating(imdb = 14.15f, kinopoisk = 16.17f),
                genres = listOf(),
                countryName = null,
                imageUrl = "http://www.bing.com/search?q=noluisse",
            ),
            Film(
                title = "veri",
                subtitle = "mucius",
                userRating = Film.UserRating(imdb = 22.23f, kinopoisk = 24.25f),
                genres = listOf(),
                countryName = null,
                imageUrl = "https://www.google.com/#q=senectus"
            ),
            Film(
                title = "",
                subtitle = "",
                userRating = Film.UserRating(imdb = 0.0f, kinopoisk = 0.0f),
                genres = listOf(),
                countryName = null,
                imageUrl = ""
            )
        ),
        modifier = Modifier.width(240.dp)
    )
}