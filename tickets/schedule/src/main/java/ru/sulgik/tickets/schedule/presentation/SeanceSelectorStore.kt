package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance

interface SeanceSelectorStore :
    Store<SeanceSelectorStore.Intent, SeanceSelectorStore.State, SeanceSelectorStore.Label> {

    sealed interface Intent {
        data class UpdateScheduleList(
            val schedule: List<Schedule>,
        ) : Intent

        data class Select(
            val seance: Schedule.Seance,
        ) : Intent
    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val selectedSeance: SelectedSeance? = null,
        val schedule: List<Schedule> = emptyList(),
    )

    sealed interface Label

}