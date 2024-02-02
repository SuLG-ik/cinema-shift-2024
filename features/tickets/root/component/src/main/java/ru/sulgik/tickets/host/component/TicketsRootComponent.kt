package ru.sulgik.tickets.host.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalTime
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalTime
import kotlinx.serialization.Serializable
import ru.sulgik.card.component.CardInputComponent
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.diChildStack
import ru.sulgik.core.component.getStore
import ru.sulgik.ticket.userinfo.component.UserInfoInput
import ru.sulgik.ticket.userinfo.component.UserInfoInputComponent
import ru.sulgik.tickets.confirmation.component.Confirmation
import ru.sulgik.tickets.confirmation.component.ConfirmationComponent
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.places.component.PlacesSelector
import ru.sulgik.tickets.places.component.PlacesSelectorComponent
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.schedule.component.SeanceSelector
import ru.sulgik.tickets.schedule.component.SeanceSelectorComponent

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
                    onSeanceSelected = { onSeanceSelected(it.convert()) },
                    scheduleStore = store,
                )
            )

            is Config.PlacesSelector -> {
                TicketsRoot.Child.PlacesSelector(
                    PlacesSelectorComponent(
                        componentContext = diComponentContext,
                        onBack = this::onBack,
                        onContinue = { onPlacesSelected(config.film, config.seance, it.convert()) },
                        selectedSeance = config.seance.convert(),
                        scheduleStore = store,
                    )
                )
            }

            is Config.OrderConfirmation -> TicketsRoot.Child.OrderConfirmation(
                ConfirmationComponent(
                    this::onBack,
                    { onOrderConfirmed(config.film, config.order, config.seance) },
                    config.convert(),
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
                            order = config.order,
                            seance = config.seance,
                            user = it.convert()
                        )
                    },
                )
            )
        }
    }


    private fun Config.OrderConfirmation.convert(): Confirmation.ConfirmationData {
        return Confirmation.ConfirmationData(
            film = Confirmation.ConfirmationData.FilmInfo(film.filmId, film.title),
            seance = Confirmation.ConfirmationData.SeanceInfo(
                date = seance.date.toJavaLocalDate(),
                time = seance.time.toJavaLocalTime(),
                hallType = seance.hallType.covert()
            ),
            place = Confirmation.ConfirmationData.PlaceInfo(
                places = order.places.map {
                    Confirmation.ConfirmationData.Place(it.row, it.column)
                }
            ),
            order = Confirmation.ConfirmationData.OrderInfo(
                totalCost = order.totalCost
            ),
        )
    }

    private fun onSeanceSelected(seanceInfo: SeanceInfo) {
        val currentFilm = store.state.film
        if (currentFilm != null)
            navigation.bringToFront(Config.PlacesSelector(currentFilm.convert(filmId), seanceInfo))
    }

    private fun onOrderConfirmed(
        film: FilmInfo,
        order: OrderInfo,
        seance: SeanceInfo,
    ) {
        navigation.bringToFront(
            Config.UserInput(
                film = film,
                order = order,
                seance = seance
            )
        )
    }

    private fun onUserInfoInput(
        film: FilmInfo,
        order: OrderInfo,
        seance: SeanceInfo,
        user: UserInfo,
    ) {
        navigation.bringToFront(
            Config.CardInput(
                film = film,
                order = order,
                seance = seance,
                user = user
            )
        )
    }

    private fun onPlacesSelected(
        film: FilmInfo,
        seance: SeanceInfo,
        order: OrderInfo,
    ) {
        navigation.bringToFront(
            Config.OrderConfirmation(
                film = film,
                order = order,
                seance = seance,
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
            val film: FilmInfo,
            val seance: SeanceInfo,
        ) : Config

        @Serializable
        data class OrderConfirmation(
            val film: FilmInfo,
            val order: OrderInfo,
            val seance: SeanceInfo,
        ) : Config

        @Serializable
        data class CardInput(
            val film: FilmInfo,
            val order: OrderInfo,
            val seance: SeanceInfo,
            val user: UserInfo,
        ) : Config

        @Serializable
        data class UserInput(
            val film: FilmInfo,
            val order: OrderInfo,
            val seance: SeanceInfo,
        ) : Config

    }

    @Serializable
    data class SeanceInfo(
        val date: LocalDate,
        val time: LocalTime,
        val hallType: HallType
    ) {
        @Serializable
        enum class HallType {
            RED, GREEN, BLUE, UNKNOWN
        }

    }

    @Serializable
    data class FilmInfo(
        val filmId: String,
        val title: String,
    )

    @Serializable
    data class OrderInfo(
        val totalCost: Int,
        val places: List<Place>,
    ) {
        @Serializable
        data class Place(
            val row: Int,
            val column: Int,
        )
    }

    @Serializable
    data class UserInfo(
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val phone: String,
    )

}

private fun TicketsRootComponent.SeanceInfo.HallType.covert(): Confirmation.ConfirmationData.HallType {
    return when (this) {
        TicketsRootComponent.SeanceInfo.HallType.RED -> Confirmation.ConfirmationData.HallType.RED
        TicketsRootComponent.SeanceInfo.HallType.GREEN -> Confirmation.ConfirmationData.HallType.GREEN
        TicketsRootComponent.SeanceInfo.HallType.BLUE -> Confirmation.ConfirmationData.HallType.BLUE
        TicketsRootComponent.SeanceInfo.HallType.UNKNOWN -> Confirmation.ConfirmationData.HallType.UNKNOWN
    }
}

private fun Film.convert(filmId: String): TicketsRootComponent.FilmInfo {
    return TicketsRootComponent.FilmInfo(
        filmId = filmId,
        title = title,
    )
}

private fun UserInfoInput.UserData.convert(): TicketsRootComponent.UserInfo {
    return TicketsRootComponent.UserInfo(
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        phone = phone,
    )
}

private fun SeanceSelector.SelectedSeance.convert(): TicketsRootComponent.SeanceInfo {
    return TicketsRootComponent.SeanceInfo(
        date = date.toKotlinLocalDate(),
        time = time.toKotlinLocalTime(),
        hallType = hallType.convert(),
    )
}

private fun Schedule.HallType.convert(): TicketsRootComponent.SeanceInfo.HallType {
    return when (this) {
        Schedule.HallType.RED -> TicketsRootComponent.SeanceInfo.HallType.RED
        Schedule.HallType.GREEN -> TicketsRootComponent.SeanceInfo.HallType.GREEN
        Schedule.HallType.BLUE -> TicketsRootComponent.SeanceInfo.HallType.BLUE
        Schedule.HallType.UNKNOWN -> TicketsRootComponent.SeanceInfo.HallType.UNKNOWN
    }
}

private fun TicketsRootComponent.SeanceInfo.convert(): PlacesSelector.SelectedSeance {
    return PlacesSelector.SelectedSeance(
        date = date.toJavaLocalDate(),
        time = time.toJavaLocalTime(),
        hallType = hallType.covertToPlacesSelector(),
    )
}

private fun TicketsRootComponent.SeanceInfo.HallType.covertToPlacesSelector(): Schedule.HallType {
    return when (this) {
        TicketsRootComponent.SeanceInfo.HallType.RED -> Schedule.HallType.RED
        TicketsRootComponent.SeanceInfo.HallType.GREEN -> Schedule.HallType.GREEN
        TicketsRootComponent.SeanceInfo.HallType.BLUE -> Schedule.HallType.BLUE
        TicketsRootComponent.SeanceInfo.HallType.UNKNOWN -> Schedule.HallType.UNKNOWN
    }
}

private fun PlacesSelector.SelectedPlaces.convert(): TicketsRootComponent.OrderInfo {
    return TicketsRootComponent.OrderInfo(
        places = places.map { it.convert() },
        totalCost = totalCost
    )
}

private fun PlacesSelector.SelectedPlace.convert(): TicketsRootComponent.OrderInfo.Place {
    return TicketsRootComponent.OrderInfo.Place(
        row = row,
        column = column
    )
}

