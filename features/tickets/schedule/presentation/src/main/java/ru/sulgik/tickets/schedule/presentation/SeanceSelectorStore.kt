package ru.sulgik.tickets.schedule.presentation

import com.arkivanov.mvikotlin.core.store.Store
import java.time.LocalDate
import java.time.LocalTime

interface SeanceSelectorStore :
    Store<SeanceSelectorStore.Intent, SeanceSelectorStore.State, SeanceSelectorStore.Label> {

    sealed interface Intent {
        data class Select(
            val date: LocalDate,
            val time: LocalTime,
        ):  Intent
    }

    data class State(
        val selectedSeance: SelectedSeance? = null,
    ) {
        data class SelectedSeance(
            val date: LocalDate,
            val time: LocalTime,
        )
    }

    sealed interface Label

}