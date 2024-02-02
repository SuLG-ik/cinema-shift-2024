package ru.sulgik.filmlist.component

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import ru.sulgik.filmlist.domain.entity.Film

interface FilmList {

    val state: Value<State>

    fun onFilmAbout(filmId: String)

    data class State(
        val isLoading: Boolean,
        val filmList: List<Film>,
    )

}