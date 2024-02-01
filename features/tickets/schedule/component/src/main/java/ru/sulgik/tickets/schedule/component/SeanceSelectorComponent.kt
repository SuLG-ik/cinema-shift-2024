package ru.sulgik.tickets.schedule.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.core.component.zip
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.presentation.SeanceSelectorStore
import java.time.LocalDate
import java.time.LocalTime

class SeanceSelectorComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onSeanceSelected: (SeanceSelector.SelectedSeance) -> Unit,
    scheduleStore: FilmScheduleStore,
) : AppComponentContext(componentContext), SeanceSelector {

    private val store: SeanceSelectorStore = getStore()

    private val scheduleState = scheduleStore.values(this)
    private val seanceSelectorState = store.values(this)

    override val state: Value<SeanceSelector.State> =
        zip(this, scheduleState, seanceSelectorState) { first, second ->
            SeanceSelector.State(
                isLoading = first.isLoading,
                isContinueAvailable = second.isContinueAvailable,
                schedule = first.schedule.orEmpty().map { schedule ->
                    SeanceSelector.State.Schedule(
                        schedule.date,
                        schedule.seances.map {
                            SeanceSelector.State.Schedule.Seance(
                                hall = it.hall.name.toState(),
                                time = it.time,
                                isSelected = it.time == second.selectedSeance?.time &&
                                        schedule.date == second.selectedSeance?.date &&
                                        it.hall.name.toState() == second.selectedSeance?.hall?.toState()
                            )
                        }
                    )
                }
            )
        }

    override fun onSelect(
        date: LocalDate,
        time: LocalTime,
        hall: SeanceSelector.State.Schedule.Hall
    ) {
        store.accept(SeanceSelectorStore.Intent.Select(date, time, hall.toData()))
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        val selectedSeance = store.state.selectedSeance
        if (selectedSeance != null)
            onSeanceSelected.invoke(
                SeanceSelector.SelectedSeance(
                    date = selectedSeance.date,
                    time = selectedSeance.time,
                    hallType = selectedSeance.hall
                )
            )
    }


}

private fun SeanceSelector.State.Schedule.Hall.toData(): Schedule.HallType {
    return when (this) {
        SeanceSelector.State.Schedule.Hall.RED -> Schedule.HallType.RED
        SeanceSelector.State.Schedule.Hall.GREEN -> Schedule.HallType.GREEN
        SeanceSelector.State.Schedule.Hall.BLUE -> Schedule.HallType.BLUE
        SeanceSelector.State.Schedule.Hall.SIMPLE -> Schedule.HallType.UNKNOWN
    }
}

private fun String.toState(): SeanceSelector.State.Schedule.Hall {
    return when (this) {
        "Red" -> SeanceSelector.State.Schedule.Hall.RED
        "Green" -> SeanceSelector.State.Schedule.Hall.GREEN
        "Blue" -> SeanceSelector.State.Schedule.Hall.BLUE
        else -> SeanceSelector.State.Schedule.Hall.SIMPLE
    }
}

private fun Schedule.HallType.toState(): SeanceSelector.State.Schedule.Hall {
    return when (this) {
        Schedule.HallType.RED -> SeanceSelector.State.Schedule.Hall.RED
        Schedule.HallType.GREEN -> SeanceSelector.State.Schedule.Hall.GREEN
        Schedule.HallType.BLUE -> SeanceSelector.State.Schedule.Hall.BLUE
        Schedule.HallType.UNKNOWN -> SeanceSelector.State.Schedule.Hall.SIMPLE
    }
}
