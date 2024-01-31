package ru.sulgik.filminfo.component

import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.filminfo.presentation.FilmInfoStore

class FilmInfoComponent(
    componentContext: DIComponentContext,
    filmId: String,
    private val onSchedule: () -> Unit,
) : AppComponentContext(componentContext), FilmInfo {

    private val coroutineScope = coroutineScope()

    private val store: FilmInfoStore = getStore(FilmInfoStore.Params(filmId))
    override fun onSchedule() {
        onSchedule.invoke()
    }

    override val state: StateFlow<FilmInfo.State> = store.stateFlow.map { it.convert() }
        .stateIn(coroutineScope, SharingStarted.Lazily, store.state.convert())

}

private fun FilmInfoStore.State.convert(): FilmInfo.State {
    return FilmInfo.State(
        isLoading = isLoading,
        film = film,
    )
}
