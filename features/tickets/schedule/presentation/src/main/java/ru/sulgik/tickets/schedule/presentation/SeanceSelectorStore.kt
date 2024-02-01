package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.tickets.domain.entity.Schedule
import java.time.LocalDate
import java.time.LocalTime

interface SeanceSelectorStore :
    Store<SeanceSelectorStore.Intent, SeanceSelectorStore.State, SeanceSelectorStore.Label> {

    sealed interface Intent {
        data class Select(
            val date: LocalDate,
            val time: LocalTime,
            val hall: Schedule.HallType,
        ) : Intent
    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val selectedSeance: SelectedSeance? = null,
    ) {
        data class SelectedSeance(
            val date: LocalDate,
            val time: LocalTime,
            val hall: Schedule.HallType,
        )
    }

    sealed interface Label

}