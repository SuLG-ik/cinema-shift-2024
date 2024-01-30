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