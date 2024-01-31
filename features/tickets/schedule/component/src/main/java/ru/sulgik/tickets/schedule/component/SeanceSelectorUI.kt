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
        isContinueAvailable = true,
        onBack = component::onBack,
        modifier = modifier,
    )
}

private fun List<SeanceSelector.State.Schedule>.toUI(): List<Seance> {
    return map { it.convert() }
}

private fun SeanceSelector.State.Schedule.convert(): Seance {
    return Seance(
        date,
        seances.map {
            Seance.ZonedSeanceTime(
                it.hall.toUI(),
                Seance.SeanceTime(
                    it.time,
                    it.isSelected,
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
