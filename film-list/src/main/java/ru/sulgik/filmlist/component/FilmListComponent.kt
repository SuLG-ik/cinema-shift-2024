package ru.sulgik.filmlist.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.filmlist.presentation.FilmListStore

class FilmListComponent(
    componentContext: DIComponentContext,
    private val onFilmInfo: (String) -> Unit,
) : AppComponentContext(componentContext), FilmList {

    private val coroutineScope = coroutineScope()

    private val store: FilmListStore = getStore()

    override val state: Value<FilmList.State> = store.values(this).map { it.toUI() }

    override fun onFilmAbout(filmId: String) {
        onFilmInfo.invoke(filmId)
    }

}

private fun FilmListStore.State.toUI(): FilmList.State {
    return FilmList.State(
        isLoading = isLoading,
        filmList = films?.map { it }.orEmpty(),
    )
}
