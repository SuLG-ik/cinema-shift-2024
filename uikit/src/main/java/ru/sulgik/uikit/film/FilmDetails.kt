package ru.sulgik.uikit.film

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.R
import ru.sulgik.uikit.UIKitRemoteImage
import ru.sulgik.uikit.tokens.UIKitShapeTokens

@Composable
fun FilmDetails(
    title: String,
    subtitle: String,
    image: String,
    ratingImdb: Float,
    ratingKinopoisk: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        FilmImage(image, modifier = Modifier.height(300.dp).fillMaxWidth())
        FilmItemTitle(title, subtitle)
        FilmRating(ratingImdb, ratingKinopoisk)
    }
}

@Composable
fun FilmRating(
    imdb: Float,
    kinopoisk: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text("Kinoposik – ${kinopoisk}", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun FilmImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    UIKitRemoteImage(
        url = imageUrl,
        contentDescription = stringResource(id = R.string.film_s_image),
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(UIKitShapeTokens.CornerMedium),
    )
}

@Composable
fun FilmItemTitle(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(title, style = MaterialTheme.typography.headlineSmall)
        Text(subtitle, style = MaterialTheme.typography.bodyLarge)
    }
}