package ru.sulgik.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface Root {

    val childStack: Value<ChildStack<Config, Child>>

    fun onNavigate(config: Config)

    @Serializable
    sealed interface Config {

        @Serializable
        data object FilmList : Config

        //TODO
        @Serializable
        data object Tickets : Config

        //TODO
        @Serializable
        data object Profile : Config

    }

    sealed interface Child {

        data class FilmList(val component: ru.sulgik.filmlist.component.FilmList) : Child

        //TODO

        data object Tickets : Child

        //TODO
        data object Profile : Child

    }
}