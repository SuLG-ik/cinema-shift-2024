package ru.sulgik.filminfo.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.ImageNotFound
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitExpandableText
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.film.FilmDetails
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens

data class Film(
    val id: String,
    val title: String,
    val subtitle: String,
    val userRating: UserRating,
    val genres: List<String>,
    val countryName: String?,
    val imageUrl: String,
    val description: String,
    val ageRating: AgeRating,
    val runtime: Int,
    val directors: List<Director>,
    val actors: List<Actor>,
    val releaseDate: String,
) {

    data class Director(
        val fullName: String,
        val id: String,
        val professions: List<Profession>,
    )


    data class Actor(
        val fullName: String,
        val id: String,
        val professions: List<Profession>,
    )

    enum class Profession {
        ACTOR,
        DIRECTOR,
    }

    data class UserRating(
        val imdb: Float,
        val kinopoisk: Float,
    )

    enum class AgeRating {
        G,
        NC17,
        PG,
        PG13,
        R,
        UNKNOWN
    }
}

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
                    Text(text = stringResource(R.string.about_top_bar))
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
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        FilmDetails(
            title = film.title,
            subtitle = film.subtitle,
            image = film.imageUrl,
            mainGenre = film.genres.first(),
            countryName = film.countryName,
            releaseYear = film.releaseDate.split(" ").last(),
            ratingImdb = film.userRating.imdb,
            backgroundColor = MaterialTheme.colorScheme.surface,
            ratingKinopoisk = film.userRating.kinopoisk,
        )
        UIKitContainedButton(
            onClick = onSchedule,
            modifier = Modifier.fillMaxWidth(),
            largeCorners = false,
        ) {
            Text(text = stringResource(R.string.view_schedule))
        }
        FilmDescription(film)
        FilmAdditionalInfo(film)
    }
}

@Composable
fun FilmDescription(film: Film, modifier: Modifier = Modifier) {
    UIKitExpandableText(film.description, modifier = modifier)
}

@Composable
fun FilmAdditionalInfo(
    film: Film,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
    ) {
        FilmReleaseDate(film, modifier = Modifier.fillMaxWidth())
        FilmGenders(film, modifier = Modifier.fillMaxWidth())
        FilmDirectors(film, modifier = Modifier.fillMaxWidth())
        FilmActors(film, modifier = Modifier.fillMaxWidth())
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmGenders(
    film: Film,
    modifier: Modifier,
) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            stringResource(R.string.about_genre),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 3.dp)
        )
        film.genres.forEach {
            FilmGenre(genre = it)
        }
    }
}

@Composable
fun FilmReleaseDate(
    film: Film,
    modifier: Modifier,
) {
    Text(buildAnnotatedString {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(stringResource(R.string.about_release_date))
        }
        append(film.releaseDate)
    }, modifier = modifier)
}

@Composable
fun FilmGenre(
    genre: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genre,
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = UIKitShapeTokens.CornerMedium,
            )
            .padding(vertical = 3.dp, horizontal = 10.dp)
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilmDirectors(
    film: Film,
    modifier: Modifier,
) {
    InfoBlock(stringResource(R.string.about_directors)) {
        Row(
            modifier = modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            film.directors.forEach {
                FilmDirector(director = it, modifier = Modifier.width(150.dp))
            }
        }
    }
}

@Composable
fun FilmDirector(
    director: Film.Director,
    modifier: Modifier = Modifier,
) {
    PersonCard(
        name = director.fullName,
        professions = director.professions,
        modifier = modifier
    )
}

@Composable
fun PersonCard(
    name: String,
    professions: List<Film.Profession>,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageNotFound(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = professions.map { it.asString() }.joinToString(", "),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun FilmActors(
    film: Film,
    modifier: Modifier,
) {
    InfoBlock(title = stringResource(R.string.about_actors), modifier = modifier) {
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            film.actors.map {
                FilmActor(actor = it, modifier = Modifier.width(150.dp))
            }
        }
    }
}

@Composable
fun FilmActor(
    actor: Film.Actor,
    modifier: Modifier = Modifier,
) {
    PersonCard(
        name = actor.fullName,
        professions = actor.professions,
        modifier = modifier
    )
}


@Composable
fun Film.Profession.asString(): String {
    return when (this) {
        Film.Profession.ACTOR -> stringResource(R.string.about_actor)
        Film.Profession.DIRECTOR -> stringResource(R.string.about_director)
    }
}

@Composable
fun InfoBlock(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(
        modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        content()
    }
}
