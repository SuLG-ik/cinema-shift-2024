package ru.sulgik.filmlist.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun FilmItemPreview() {
    FilmItem(
        film = Film(
            title = "dicta",
            subtitle = "inciderint",
            userRating = Film.UserRating(imdb = 4.5f, kinopoisk = 6.7f),
            genres = listOf("Драма"),
            countryName = null,
            imageUrl = "https://www.google.com/#q=vim",
        )
    )
}