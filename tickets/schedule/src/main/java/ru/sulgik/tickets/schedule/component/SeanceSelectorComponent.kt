package ru.sulgik.tickets.schedule.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.decompose.value.subscribe
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.ValueContainer
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.presentation.SeanceSelectorStore

class SeanceSelectorComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onSeanceSelected: (SelectedSeance) -> Unit,
    scheduleStore: FilmScheduleStore,
) : AppComponentContext(componentContext), SeanceSelector {

    private val store: SeanceSelectorStore = getStore()

    private val state = store.values(this)

    init {
        scheduleStore.values(this)
            .subscribe(lifecycle) {
                val schedules = it.schedule
                if (schedules != null)
                    store.accept(SeanceSelectorStore.Intent.UpdateScheduleList(schedules))
            }
    }

    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }

    override val schedule: Value<List<Schedule>> = state.map { it.schedule }

    override val selectedSeance: Value<ValueContainer<SelectedSeance?>> =
        state.map { ValueContainer(it.selectedSeance) }

    override fun onSelect(seance: Schedule.Seance) {
        store.accept(SeanceSelectorStore.Intent.Select(seance))
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        val selectedSeance = store.state.selectedSeance
        if (selectedSeance != null)
            onSeanceSelected.invoke(selectedSeance)
    }

}