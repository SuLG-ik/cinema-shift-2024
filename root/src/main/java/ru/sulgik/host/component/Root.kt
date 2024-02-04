package ru.sulgik.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sulgik.profile.host.component.ProfileRoot

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

        data class Films(val component: ru.sulgik.host.component.films.Films) : Child

        //TODO

        data object Tickets : Child

        data class Profile(
            val component: ProfileRoot
        ) : Child

    }
}