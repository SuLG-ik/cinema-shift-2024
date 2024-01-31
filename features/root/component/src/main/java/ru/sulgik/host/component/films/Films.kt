package ru.sulgik.host.component.films

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface Films {

    val childState: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class FilmList(val component: ru.sulgik.filmlist.component.FilmList) : Child
        data class FilmInfo(val component: ru.sulgik.filminfo.component.FilmInfo) : Child
    }

}