package ru.sulgik.tickets.schedule.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.schedule.ui.ScheduleScreen
import ru.sulgik.tickets.schedule.ui.Seance

@Composable
fun SeanceSelectorUI(
    component: SeanceSelector,
    modifier: Modifier = Modifier,
) {
    val state = component.state.subscribeAsState()

    ScheduleScreen(
        seances = state.value.schedule?.toUI(),
        isContinueAvailable = state.value.isContinueAvailable,
        onBack = component::onBack,
        onContinue = component::onContinue,
        onSelect = { component.onSelect(it.time.date, it.time.time, it.hallType.toData()) },
        modifier = modifier,
    )
}

private fun Seance.HallType.toData(): SeanceSelector.State.Schedule.Hall {
    return when (this) {
        Seance.HallType.RED -> SeanceSelector.State.Schedule.Hall.RED
        Seance.HallType.GREEN -> SeanceSelector.State.Schedule.Hall.GREEN
        Seance.HallType.BLUE -> SeanceSelector.State.Schedule.Hall.BLUE
        Seance.HallType.SIMPLE -> SeanceSelector.State.Schedule.Hall.SIMPLE
    }
}

private fun List<SeanceSelector.State.Schedule>.toUI(): List<Seance> {
    return map { it.convert() }
}

private fun SeanceSelector.State.Schedule.convert(): Seance {
    return Seance(
        date = date,
        time = seances.map {
            Seance.ZonedSeanceTime(
                hallType = it.hall.toUI(),
                time = Seance.SeanceTime(
                    time = it.time,
                    date = date,
                    isSelected = it.isSelected,
                )
            )
        }
    )
}

private fun SeanceSelector.State.Schedule.Hall.toUI(): Seance.HallType {
    return when (this) {
        SeanceSelector.State.Schedule.Hall.RED -> Seance.HallType.RED
        SeanceSelector.State.Schedule.Hall.GREEN -> Seance.HallType.GREEN
        SeanceSelector.State.Schedule.Hall.BLUE -> Seance.HallType.BLUE
        SeanceSelector.State.Schedule.Hall.SIMPLE -> Seance.HallType.SIMPLE
    }
}
