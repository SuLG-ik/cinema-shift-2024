package ru.sulgik.filmlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitRemoteImage
import ru.sulgik.uikit.film.FilmDetails
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens

data class Film(
    val id: String,
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
fun FilmList(
    films: List<Film>,
    onFilmAbout: (Film) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        contentPadding = PaddingValues(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
    ) {
        items(films, contentType = { "film" }, key = { it.id }) {
            FilmItem(
                film = it,
                onFilmAbout = { onFilmAbout(it) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(
    films: List<Film>,
    onFilmAbout: (Film) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Афиша") })
        },
        modifier = modifier
    ) {
        FilmList(
            films = films,
            onFilmAbout = onFilmAbout,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Composable
fun FilmItem(
    film: Film,
    onFilmAbout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onFilmAbout,
        ),
    ) {
        Column(
            modifier = Modifier.padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
        ) {
            FilmDetails(
                title = film.title,
                subtitle = film.subtitle,
                image = film.imageUrl,
                ratingImdb = film.userRating.imdb,
                ratingKinopoisk = film.userRating.kinopoisk,
            )
            FilmAboutButton(onClick = onFilmAbout, modifier = Modifier.fillMaxWidth())
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
fun FilmImage(
    film: Film,
    modifier: Modifier = Modifier,
) {
    UIKitRemoteImage(
        url = film.imageUrl,
        contentDescription = stringResource(R.string.film_s_image),
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(MaterialTheme.shapes.large),
    )
}

@Composable
fun FilmItemTitle(
    film: Film,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(film.title, style = MaterialTheme.typography.headlineMedium)
        Text(film.subtitle, style = MaterialTheme.typography.bodyLarge)
    }
}