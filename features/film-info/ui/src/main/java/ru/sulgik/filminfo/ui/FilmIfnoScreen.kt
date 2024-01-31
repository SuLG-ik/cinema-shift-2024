package ru.sulgik.filminfo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.film.FilmDetails
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens

data class Film(
    val title: String,
    val description: String,
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
    onBack: () -> Unit,
    onSchedule: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = "О фильме")
                },
                onBack = onBack,
            )
        },
        modifier = modifier,
    ) {
        if (film != null) FilmInfo(
            film = film,
            onSchedule = onSchedule,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun FilmInfo(
    film: Film,
    onSchedule: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
            .verticalScroll(rememberScrollState())
    ) {
        FilmDetails(
            title = film.title,
            subtitle = film.subtitle,
            image = film.imageUrl,
            ratingImdb = film.userRating.imdb,
            ratingKinopoisk = film.userRating.imdb,
        )
        UIKitContainedButton(onClick = onSchedule) {
            Text(text = stringResource(R.string.view_schedule))
        }
        Text(film.description)
    }
}