package ru.sulgik.tickets.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.sulgik.card.component.CardInputComponent
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.core.component.getStore
import ru.sulgik.tickets.confirmation.component.ConfirmationComponent
import ru.sulgik.tickets.confirmation.domain.entity.ConfirmationData
import ru.sulgik.tickets.domain.entity.SelectedFilm
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.domain.entity.SelectedUser
import ru.sulgik.tickets.places.component.PlacesSelectorComponent
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.component.SeanceSelectorComponent
import ru.sulgik.tickets.userinfo.component.UserInfoInputComponent

class TicketsRootComponent(
    componentContext: DIComponentContext,
    private val filmId: String,
    private val onBack: () -> Unit,
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
                    onSeanceSelected = { onSeanceSelected(it) },
                    scheduleStore = store,
                )
            )

            is Config.PlacesSelector -> {
                TicketsRoot.Child.PlacesSelector(
                    PlacesSelectorComponent(
                        componentContext = diComponentContext,
                        onBack = this::onBack,
                        onContinue = { onPlacesSelected(config.film, config.seance, it) },
                        selectedSeance = config.seance,
                        scheduleStore = store,
                    )
                )
            }

            is Config.OrderConfirmation -> TicketsRoot.Child.OrderConfirmation(
                ConfirmationComponent(
                    onBack = this::onBack,
                    onContinue = { onOrderConfirmed(config.film, config.seance, config.places) },
                    state = ConfirmationData(
                        film = config.film,
                        seance = config.seance,
                        places = config.places,
                    ),
                )
            )

            is Config.CardInput -> TicketsRoot.Child.CardInput(
                CardInputComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    onContinue = {

                    },
                )
            )

            is Config.UserInput -> TicketsRoot.Child.UserInput(
                UserInfoInputComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    onContinue = {
                        onUserInfoInput(
                            film = config.film,
                            places = config.places,
                            seance = config.seance,
                            user = it
                        )
                    },
                )
            )
        }
    }


    private fun onSeanceSelected(seance: SelectedSeance) {
        val currentFilm = store.state.film
        if (currentFilm != null)
            navigation.bringToFront(
                Config.PlacesSelector(
                    film = SelectedFilm(
                        filmId = filmId,
                        title = currentFilm.title,
                    ),
                    seance = seance
                )
            )
    }

    private fun onOrderConfirmed(
        film: SelectedFilm,
        seance: SelectedSeance,
        places: SelectedPlaces,
    ) {
        navigation.bringToFront(
            Config.UserInput(
                film = film,
                seance = seance,
                places = places,
            )
        )
    }

    private fun onUserInfoInput(
        film: SelectedFilm,
        seance: SelectedSeance,
        places: SelectedPlaces,
        user: SelectedUser,
    ) {
        navigation.bringToFront(
            Config.CardInput(
                film = film,
                seance = seance,
                places = places,
                user = user
            )
        )
    }

    private fun onPlacesSelected(
        film: SelectedFilm,
        seance: SelectedSeance,
        places: SelectedPlaces,
    ) {
        navigation.bringToFront(
            Config.OrderConfirmation(
                film = film,
                seance = seance,
                places = places,
            )
        )
    }

    private fun onBack() {
        navigation.pop {
            if (!it)
                onBack.invoke()
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object SeanceSelector : Config

        @Serializable
        data class PlacesSelector(
            val film: SelectedFilm,
            val seance: SelectedSeance,
        ) : Config

        @Serializable
        data class OrderConfirmation(
            val film: SelectedFilm,
            val seance: SelectedSeance,
            val places: SelectedPlaces,
        ) : Config

        @Serializable
        data class CardInput(
            val film: SelectedFilm,
            val seance: SelectedSeance,
            val places: SelectedPlaces,
            val user: SelectedUser,
        ) : Config

        @Serializable
        data class UserInput(
            val film: SelectedFilm,
            val seance: SelectedSeance,
            val places: SelectedPlaces,
        ) : Config

    }

}