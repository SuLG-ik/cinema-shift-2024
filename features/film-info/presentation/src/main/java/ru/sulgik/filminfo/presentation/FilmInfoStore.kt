package ru.sulgik.filminfo.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.filminfo.domain.entity.Film

interface FilmInfoStore : Store<FilmInfoStore.Intent, FilmInfoStore.State, FilmInfoStore.Label> {

    data class Params(
        val filmId: String,
    )

    sealed interface Intent {

    }

    data class State(
        val isLoading: Boolean = true,
        val film: Film? = null,
    )

    sealed interface Label

}