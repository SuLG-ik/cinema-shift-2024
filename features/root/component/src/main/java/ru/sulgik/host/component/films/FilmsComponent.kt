package ru.sulgik.host.component.films

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.filminfo.component.FilmInfoComponent
import ru.sulgik.filmlist.component.FilmListComponent
import ru.sulgik.tickets.host.component.TicketsRootComponent

class FilmsComponent(componentContext: DIComponentContext) : AppComponentContext(componentContext),
    Films {

    private val navigation = StackNavigation<Config>()
    override val childState: Value<ChildStack<*, Films.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.FilmsList,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config,
        diComponentContext: DIComponentContext
    ): Films.Child {
        return when (config) {
            is Config.FilmInfo -> Films.Child.FilmInfo(
                FilmInfoComponent(
                    componentContext = diComponentContext,
                    filmId = config.filmId,
                    onSchedule =  { onSchedule(config.filmId) },
                    onBack = this::onBack,
                )
            )

            Config.FilmsList -> Films.Child.FilmList(
                FilmListComponent(
                    diComponentContext,
                    onFilmInfo = this::onFilmAbout,
                )
            )

            is Config.TicketOrder -> Films.Child.TicketOrder(
                TicketsRootComponent(
                    componentContext = diComponentContext,
                    filmId = config.filmId,
                )
            )
        }
    }

    private fun onFilmAbout(filmId: String) {
        navigation.bringToFront(Config.FilmInfo(filmId))
    }

    private fun onBack() {
        navigation.pop()
    }


    private fun onSchedule(filmId: String) {
        navigation.bringToFront(Config.TicketOrder(filmId))
    }


    @Serializable
    sealed interface Config {

        @Serializable
        data object FilmsList : Config

        @Serializable
        data class FilmInfo(val filmId: String) : Config

        @Serializable
        data class TicketOrder(val filmId: String) : Config

    }

}