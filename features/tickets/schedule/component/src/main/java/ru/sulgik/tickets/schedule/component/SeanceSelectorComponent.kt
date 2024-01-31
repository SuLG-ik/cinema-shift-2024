package ru.sulgik.tickets.schedule.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.core.component.zip
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.presentation.SeanceSelectorStore
import java.time.LocalDate
import java.time.LocalTime

class SeanceSelectorComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    scheduleStore: FilmScheduleStore,
) : AppComponentContext(componentContext), SeanceSelector {

    private val store: SeanceSelectorStore = getStore()

    private val scheduleState = scheduleStore.values(this)
    private val seanceSelectorState = store.values(this)

    override val state: Value<SeanceSelector.State> =
        zip(this, scheduleState, seanceSelectorState) { first, second ->
            SeanceSelector.State(
                isLoading = first.isLoading, schedule = first.schedule.orEmpty().map { schedule ->
                    SeanceSelector.State.Schedule(
                        schedule.date,
                        schedule.seances.map {
                            SeanceSelector.State.Schedule.Seance(
                                hall = it.hall.name.toState(),
                                time = it.time,
                                isSelected = it.time == second.selectedSeance?.time && schedule.date == second.selectedSeance?.date,
                            )
                        }
                    )
                }
            )
        }

    override fun onSelect(date: LocalDate, time: LocalTime) {
        store.accept(SeanceSelectorStore.Intent.Select(date, time))
    }

    override fun onBack() {
        onBack.invoke()
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
