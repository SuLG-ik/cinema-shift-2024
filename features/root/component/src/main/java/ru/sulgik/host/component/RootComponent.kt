package ru.sulgik.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.host.component.films.FilmsComponent

class RootComponent(
    componentContext: DIComponentContext
) : AppComponentContext(componentContext), Root {

    private val navigation = StackNavigation<Root.Config>()

    override val childStack: Value<ChildStack<Root.Config, Root.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Root.Config.FilmList,
        serializer = Root.Config.serializer(),
        handleBackButton = true,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Root.Config,
        diComponentContext: DIComponentContext
    ): Root.Child {
        return when (config) {
            Root.Config.FilmList -> Root.Child.Films(
                component = FilmsComponent(
                    diComponentContext,
                )
            )

            Root.Config.Profile -> Root.Child.Profile
            Root.Config.Tickets -> Root.Child.Tickets
        }
    }

    override fun onNavigate(config: Root.Config) {
        TODO("Not yet implemented")
    }

}