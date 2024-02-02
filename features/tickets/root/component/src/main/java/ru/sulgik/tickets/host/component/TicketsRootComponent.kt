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
import ru.sulgik.tickets.confirmation.component.Confirmation
import ru.sulgik.tickets.confirmation.component.ConfirmationComponent
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
                    onSeanceSelected = this::onSeanceSelected,
                    scheduleStore = store,
                )
            )

            is Config.PlacesSelector -> {
                val seance = PlacesSelector.SelectedSeance(
                    date = config.seanceDate.toJavaLocalDate(),
                    time = config.seanceTime.toJavaLocalTime(),
                    hallType = config.hallType.covert(),
                )
                TicketsRoot.Child.PlacesSelector(
                    PlacesSelectorComponent(
                        componentContext = diComponentContext,
                        onBack = this::onBack,
                        onContinue = { onPlacesSelected(seance, it) },
                        selectedSeance = seance,
                        scheduleStore = store,
                    )
                )
            }

            is Config.OrderConfirmation -> TicketsRoot.Child.OrderConfirmation(
                ConfirmationComponent(
                    this::onBack,
                    this::onOrderConfirmed,
                    config.convert(),
                )
            )

            Config.CardInput -> TicketsRoot.Child.CardInput(
                CardInputComponent(
                    componentContext = diComponentContext,
                    onBack = this::onBack,
                    onContinue = {},
                )
            )
        }
    }


    private fun TicketsRootComponent.Config.OrderConfirmation.convert(): Confirmation.ConfirmationData {
        return Confirmation.ConfirmationData(
            film = Confirmation.ConfirmationData.FilmInfo(filmId, filmTitle),
            seance = Confirmation.ConfirmationData.SeanceInfo(
                date = seanceDate.toJavaLocalDate(),
                time = seanceTime.toJavaLocalTime(),
                hallType = hallType.covert()
            ),
            place = Confirmation.ConfirmationData.PlaceInfo(
                places = places.map {
                    Confirmation.ConfirmationData.Place(it.row, it.column)
                }
            ),
            order = Confirmation.ConfirmationData.OrderInfo(
                totalCost = totalCost
            ),
        )
    }

    private fun onSeanceSelected(selectedSeance: SeanceSelector.SelectedSeance) {
        navigation.bringToFront(
            Config.PlacesSelector(
                selectedSeance.date.toKotlinLocalDate(),
                selectedSeance.time.toKotlinLocalTime(),
                selectedSeance.hallType.convert(),
            )
        )
    }

    private fun onOrderConfirmed() {
        navigation.bringToFront(Config.CardInput)
    }

    private fun onPlacesSelected(
        selectedSeance: PlacesSelector.SelectedSeance,
        selectedPlaces: PlacesSelector.SelectedPlaces,
    ) {
        val currentFilm = store.state.film
        if (currentFilm != null)
            navigation.bringToFront(
                Config.OrderConfirmation(
                    filmId = filmId,
                    filmTitle = currentFilm.title,
                    places = selectedPlaces.places.map {
                        Config.OrderConfirmation.Place(
                            row = it.row,
                            column = it.column
                        )
                    },
                    totalCost = selectedPlaces.totalCost,
                    seanceDate = selectedSeance.date.toKotlinLocalDate(),
                    seanceTime = selectedSeance.time.toKotlinLocalTime(),
                    hallType = Config.OrderConfirmation.HallType.GREEN

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
            val seanceDate: LocalDate,
            val seanceTime: LocalTime,
            val hallType: HallType
        ) : Config {
            @Serializable
            enum class HallType {
                RED, GREEN, BLUE, UNKNOWN
            }
        }

        @Serializable
        data class OrderConfirmation(
            val filmId: String,
            val filmTitle: String,
            val places: List<Place>,
            val totalCost: Int,
            val seanceDate: LocalDate,
            val seanceTime: LocalTime,
            val hallType: HallType
        ) : Config {
            @Serializable
            enum class HallType {
                RED, GREEN, BLUE, UNKNOWN
            }

            @Serializable
            data class Place(
                val row: Int,
                val column: Int,
            )


        }

        @Serializable
        data object CardInput : Config

    }

    private fun Schedule.HallType.convert(): TicketsRootComponent.Config.PlacesSelector.HallType {
        return when (this) {
            Schedule.HallType.RED -> TicketsRootComponent.Config.PlacesSelector.HallType.RED
            Schedule.HallType.GREEN -> TicketsRootComponent.Config.PlacesSelector.HallType.GREEN
            Schedule.HallType.BLUE -> TicketsRootComponent.Config.PlacesSelector.HallType.BLUE
            Schedule.HallType.UNKNOWN -> TicketsRootComponent.Config.PlacesSelector.HallType.UNKNOWN
        }
    }

    private fun TicketsRootComponent.Config.PlacesSelector.HallType.covert(): Schedule.HallType {
        return when (this) {
            TicketsRootComponent.Config.PlacesSelector.HallType.RED -> Schedule.HallType.RED
            TicketsRootComponent.Config.PlacesSelector.HallType.GREEN -> Schedule.HallType.GREEN
            TicketsRootComponent.Config.PlacesSelector.HallType.BLUE -> Schedule.HallType.BLUE
            TicketsRootComponent.Config.PlacesSelector.HallType.UNKNOWN -> Schedule.HallType.UNKNOWN
        }
    }

    private fun TicketsRootComponent.Config.OrderConfirmation.HallType.covert(): Confirmation.ConfirmationData.HallType {
        return when (this) {
            TicketsRootComponent.Config.OrderConfirmation.HallType.RED -> Confirmation.ConfirmationData.HallType.RED
            TicketsRootComponent.Config.OrderConfirmation.HallType.GREEN -> Confirmation.ConfirmationData.HallType.GREEN
            TicketsRootComponent.Config.OrderConfirmation.HallType.BLUE -> Confirmation.ConfirmationData.HallType.BLUE
            TicketsRootComponent.Config.OrderConfirmation.HallType.UNKNOWN -> Confirmation.ConfirmationData.HallType.UNKNOWN
        }
    }

}
