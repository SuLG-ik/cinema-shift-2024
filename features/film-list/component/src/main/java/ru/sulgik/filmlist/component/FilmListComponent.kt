package ru.sulgik.filmlist.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.filmlist.domain.entity.Film
import ru.sulgik.filmlist.presentation.FilmListStore

class FilmListComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), FilmList {

    private val coroutineScope = coroutineScope()

    private val store: FilmListStore = getStore()

    override val state: StateFlow<FilmList.State> = store.stateFlow.map { it.convert() }
        .stateIn(coroutineScope, SharingStarted.Lazily, store.state.convert())

    override fun onFilmAbout(film: Film) {
        TODO("Not yet implemented")
    }

}

private fun FilmListStore.State.convert(): FilmList.State {
    return FilmList.State(
        isLoading = isLoading,
        filmList = films?.map { it }.orEmpty(),
    )
}
