package ru.sulgik.tickets.schedule.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTab
import ru.sulgik.uikit.UIKitTabRow
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import java.time.LocalDate
import java.time.LocalTime

data class Seance(
    val date: LocalDate,
    val time: List<ZonedSeanceTime>,
) {
    data class ZonedSeanceTime(
        val hallType: HallType,
        val time: SeanceTime,
    )

    enum class HallType {
        RED, GREEN, BLUE, SIMPLE
    }

    data class SeanceTime(
        val time: LocalTime,
        val isSelected: Boolean,
    )
}

@Composable
fun ScheduleScreen(
    seances: List<Seance>?,
    isContinueAvailable: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = "Расписание")
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            UIKitContainedButton(
                onClick = { /*TODO*/ },
                largeCorners = false,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Продолжить")
            }
        },
        modifier = modifier,
    ) {
        if (seances != null) {
            ScheduleInfo(
                seances = seances,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }
}

@Composable
fun ScheduleInfo(
    seances: List<Seance>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
            .verticalScroll(rememberScrollState())
    ) {
        ScheduleDates(
            seances, modifier = Modifier.fillMaxWidth()
        )
        seances.forEach {
            Text(text = it.date.toString() + it.time.toString())
        }
    }
}

@Composable
fun ScheduleDates(
    seances: List<Seance>,
    modifier: Modifier = Modifier,
) {
    val selectedItem = remember { mutableIntStateOf(1) }
    UIKitTabRow(
        modifier = modifier,
        selectedTabIndex = selectedItem.intValue,
    ) {
        seances.mapIndexed { index, it ->
            UIKitTab(
                selected = selectedItem.intValue == index,
                onClick = { selectedItem.intValue = index },
            ) {
                Text(
                    "${it.date.dayOfMonth}.${it.date.monthValue}",
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }
}