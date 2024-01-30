package ru.sulgik.filmlist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitPaddingDefaultTokens

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

@Composable
fun FilmListScreen(
    films: List<Film>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(films) {
            FilmItem(
                film = it,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun FilmItem(
    film: Film,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
        ) {
            FilmItemTitle(film)
            FilmRating(film.userRating)
            FilmAboutButton(onClick = {}, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun FilmAboutButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitContainedButton(
        onClick = onClick,
        largeCorners = false,
        modifier = modifier,
    ) {
        Text(stringResource(id = R.string.more_info))
    }
}

@Composable
fun FilmRating(
    rating: Film.UserRating,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text("Kinoposik – ${rating.kinopoisk}", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun FilmItemTitle(
    film: Film,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(film.title, style = MaterialTheme.typography.bodyLarge)
        Text(film.subtitle, style = MaterialTheme.typography.bodySmall)
    }
}