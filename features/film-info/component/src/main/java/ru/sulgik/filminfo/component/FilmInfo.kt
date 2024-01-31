package ru.sulgik.filminfo.component

import kotlinx.coroutines.flow.StateFlow
import ru.sulgik.filminfo.domain.entity.Film

interface FilmInfo {

    fun onSchedule()

    val state: StateFlow<State>

    data class State(
        val isLoading: Boolean,
        val film: Film?,
    )

}