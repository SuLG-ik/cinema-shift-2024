package ru.sulgik.uikit.film

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.R
import ru.sulgik.uikit.UIKitRatingStars
import ru.sulgik.uikit.UIKitRemoteImage
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens

@Composable
fun FilmDetails(
    title: String,
    subtitle: String,
    image: String,
    ratingImdb: Float,
    ratingKinopoisk: Float,
    countryName: String?,
    releaseYear: String,
    mainGenre: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        FilmImage(
            imageUrl = image,
            countryName = countryName,
            releaseYear = releaseYear,
            mainGenre = mainGenre,
            backgroundColor = backgroundColor,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        )
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
    Row(
        modifier = modifier,
    ) {
        Rating(
            service = "IMDB",
            value = imdb,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
        )
        Rating(
            service = "Кинопоиск",
            value = kinopoisk,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
        )
    }
}

@Composable
fun Rating(service: String, value: Float, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UIKitRatingStars(value)
        Text("$service – ${value}", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FilmImage(
    imageUrl: String,
    countryName: String?,
    releaseYear: String,
    mainGenre: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(modifier.clip(UIKitShapeTokens.CornerMedium.copy(bottomEnd = CornerSize(0)))) {
        UIKitRemoteImage(
            url = imageUrl,
            contentDescription = stringResource(id = R.string.film_s_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        ShortOnImageFilmInfo(
            countryName = countryName,
            releaseYear = releaseYear,
            mainGenre = mainGenre,
            backgroundColor = backgroundColor,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}

@Composable
fun ShortOnImageFilmInfo(
    countryName: String?,
    releaseYear: String,
    mainGenre: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = UIKitShapeTokens.CornerMedium.copy(
                    bottomEnd = CornerSize(0),
                    bottomStart = CornerSize(0),
                    topEnd = CornerSize(0)
                )
            )
            .padding(
                horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding,
                vertical = UIKitPaddingDefaultTokens.DefaultContentPadding - 5.dp,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(mainGenre, style = MaterialTheme.typography.titleMedium)
        if (countryName != null)
            Text("$countryName, $releaseYear", style = MaterialTheme.typography.bodyMedium)
        else
            Text("$ $releaseYear", style = MaterialTheme.typography.bodyMedium)
    }
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
        if (subtitle != title)
            Text(subtitle, style = MaterialTheme.typography.bodyLarge)
    }
}