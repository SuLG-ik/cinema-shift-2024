package ru.sulgik.tickets.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule

interface FilmScheduleStore :
    Store<FilmScheduleStore.Intent, FilmScheduleStore.State, FilmScheduleStore.Label> {

    data class Params(
        val filmId: String,
    )

    sealed interface Intent {

    }

    data class State(
        val isLoading: Boolean = true,
        val film: Film? = null,
        val schedule: List<Schedule>? = null,
    )

    sealed interface Label

}