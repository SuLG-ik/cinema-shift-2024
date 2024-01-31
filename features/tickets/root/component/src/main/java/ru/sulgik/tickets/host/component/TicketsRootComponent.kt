package ru.sulgik.tickets.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.core.component.getStore
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.component.SeanceSelectorComponent

class TicketsRootComponent(
    componentContext: DIComponentContext,
    filmId: String,
) : AppComponentContext(componentContext), TicketsRoot {

    private val store: FilmScheduleStore = getStore(FilmScheduleStore.Params(filmId))

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, TicketsRoot.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.SeanceSelector,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config, diComponentContext: DIComponentContext
    ): TicketsRoot.Child {
        return when (config) {
            Config.SeanceSelector -> TicketsRoot.Child.SeanceSelector(
                SeanceSelectorComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    scheduleStore = store,
                )
            )
        }
    }

    private fun onBack() {
        navigation.pop()
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object SeanceSelector : Config
    }

}