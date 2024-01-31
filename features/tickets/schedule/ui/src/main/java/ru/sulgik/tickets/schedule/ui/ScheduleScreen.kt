package ru.sulgik.tickets.schedule.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.uikit.UIKitContainedButton
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
        seances.forEach {
            Text(text = it.date.toString() + it.time.toString())
        }
    }
}