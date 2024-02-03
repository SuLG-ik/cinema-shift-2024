package ru.sulgik.profile.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.login.component.LoginComponent
import ru.sulgik.profile.edit.component.ProfileEditComponent

class ProfileRootComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), ProfileRoot {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, ProfileRoot.Child>> = diChildStack(
        source = navigation,
        initialConfiguration = Config.Profile,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = this::createChild,
    )

    private fun createChild(
        config: Config, diComponentContext: DIComponentContext
    ): ProfileRoot.Child {
        return when (config) {
            Config.Login -> ProfileRoot.Child.Login(
                LoginComponent(
                    componentContext = diComponentContext,
                    this::onAuthorized,
                )
            )

            Config.Profile -> ProfileRoot.Child.Edit(
                ProfileEditComponent(
                    componentContext = diComponentContext,
                    onUnauthorized = this::onUnauthorized,
                )
            )
        }
    }

    private fun onAuthorized() {
        navigation.replaceAll(Config.Profile)
    }

    private fun onUnauthorized() {
        navigation.replaceAll(Config.Login)
    }

    @Serializable
    sealed interface Config {

        @Serializable
        object Profile : Config

        @Serializable
        object Login : Config

    }

}