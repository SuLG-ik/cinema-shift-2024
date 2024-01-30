package ru.sulgik.filmlist.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.filmlist.domain.entity.Film

interface FilmListStore : Store<FilmListStore.Intent, FilmListStore.State, FilmListStore.Label> {

    sealed interface Intent {

    }

    data class State(
        val isLoading: Boolean = true,
        val films: List<Film>? = null,
    )

    sealed interface Label

}