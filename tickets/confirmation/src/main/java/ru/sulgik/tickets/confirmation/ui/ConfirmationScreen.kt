package ru.sulgik.tickets.confirmation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sulgik.core.datetime.LocalDateTimeFormatter
import ru.sulgik.tickets.confirmation.component.R
import ru.sulgik.tickets.confirmation.domain.entity.ConfirmationData
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import java.time.LocalDate
import java.time.LocalTime


data class Confirmation(
    val film: FilmInfo,
    val seance: SeanceInfo,
    val place: PlaceInfo,
    val order: OrderInfo,
) {

    data class FilmInfo(
        val title: String,
    )

    data class SeanceInfo(
        val date: LocalDate,
        val time: LocalTime,
        val hallType: HallType,
    )

    enum class HallType {
        RED, GREEN, BLUE, UNKNOWN,
    }

    data class PlaceInfo(
        val places: List<Place>,
    )

    data class Place(
        val row: Int,
        val column: Int,
    )

    data class OrderInfo(
        val totalCost: Int,
    )

}

@Composable
fun ConfirmationScreen(
    confirmation: ConfirmationData,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.confirmation))
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            UIKitContainedButton(
                onClick = onContinue,
                largeCorners = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
            ) {
                Text(text = stringResource(R.string.continue_button))
            }
        },
        modifier = modifier,
    ) {
        OrderCard(
            confirmation = confirmation,
            modifier = Modifier
                .padding(it)
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun OrderCard(
    confirmation: ConfirmationData,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
            verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
        ) {
            Title(stringResource(R.string.confirmation_await))
            ConfirmationInfo(
                confirmation = confirmation,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }
    }
}

@Composable
fun ConfirmationInfo(
    confirmation: ConfirmationData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
    ) {
        val formatter = LocalDateTimeFormatter.current
        TitledText(
            title = stringResource(R.string.movie),
            text = confirmation.film.title,
        )
        TitledText(
            title = stringResource(R.string.hall),
            text = confirmation.seance.hallType.format(),
        )
        TitledText(
            title = stringResource(R.string.date_time),
            text = formatter.formatDateWithTime(confirmation.seance.date, confirmation.seance.time)
        )
        TitledText(
            title = stringResource(R.string.places),
            text = confirmation.places.places.groupBy { it.row }
                .map {
                    stringResource(
                        R.string.row_columns,
                        it.key,
                        it.value.joinToString(", ") { it.column.toString() },
                    )
                },
        )
        SmallTitle(
            text = stringResource(R.string.total_cost, confirmation.places.totalCost),
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
private fun SelectedSeance.HallType.format(): String {
    return when (this) {
        SelectedSeance.HallType.RED -> stringResource(R.string.red_hall)
        SelectedSeance.HallType.GREEN -> stringResource(R.string.green_hall)
        SelectedSeance.HallType.BLUE -> stringResource(R.string.blue_hall)
        SelectedSeance.HallType.UNKNOWN -> stringResource(R.string.unknown_hall)
    }
}

@Composable
fun Title(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier,
    )
}

@Composable
fun SmallTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
    )
}

@Composable
fun TitledText(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun TitledText(
    title: String,
    text: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        text.forEach {
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}