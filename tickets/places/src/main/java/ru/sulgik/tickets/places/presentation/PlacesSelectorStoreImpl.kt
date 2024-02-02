package ru.sulgik.tickets.places.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.places.domain.entity.PlaceWithState
import ru.sulgik.tickets.places.domain.usecase.FindPlacesBySeanceInScheduleUseCase
import ru.sulgik.tickets.places.domain.usecase.MakePlaceWithStateUseCase
import ru.sulgik.tickets.places.domain.usecase.SelectPlaceUseCase
import ru.sulgik.tickets.places.domain.usecase.UnselectPlaceUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class PlacesSelectorStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    selectPlaceUseCase: SelectPlaceUseCase,
    unselectPlaceUseCase: UnselectPlaceUseCase,
    makePlaceWithStateUseCase: MakePlaceWithStateUseCase,
    findPlacesBySeanceInScheduleUseCase: FindPlacesBySeanceInScheduleUseCase,
) : PlacesSelectorStore,
    Store<PlacesSelectorStore.Intent, PlacesSelectorStore.State, PlacesSelectorStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "PlacesSelectorStoreImpl",
        initialState = PlacesSelectorStore.State(),
        reducer = {
            when (it) {
                is Message.UpdateSelectedPlaces -> copy(
                    isContinueAvailable = it.selectedPlaces.places.isNotEmpty(),
                    selectedPlaces = it.selectedPlaces,
                    placeWithState = it.placesWithState,
                    places = it.places ?: places
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<PlacesSelectorStore.Intent.UpdatePlaceList> {
                val places = findPlacesBySeanceInScheduleUseCase(
                    schedule = it.schedule,
                    seance = it.seance,
                )
                val placesWithState = makePlaceWithStateUseCase(
                    places = places,
                    selectedPlaces = state().selectedPlaces.places,
                )
                dispatch(
                    Message.UpdateSelectedPlaces(
                        selectedPlaces = state().selectedPlaces,
                        placesWithState = placesWithState,
                        places = places,
                    )
                )
            }
            onIntent<PlacesSelectorStore.Intent.Select> { intent ->
                val selectedPlaces = when (intent.place.state) {
                    PlaceWithState.State.SELECTED -> unselectPlaceUseCase(
                        selectedPlaces = state().selectedPlaces,
                        place = intent.place.place,
                    )

                    PlaceWithState.State.UNSELECTED -> selectPlaceUseCase(
                        selectedPlaces = state().selectedPlaces,
                        place = intent.place.place,
                    )

                    else -> return@onIntent
                }
                val placesWithState =
                    makePlaceWithStateUseCase(state().places, selectedPlaces.places)
                dispatch(
                    Message.UpdateSelectedPlaces(
                        selectedPlaces = selectedPlaces,
                        placesWithState = placesWithState,
                    )
                )
            }
        },
    ) {

    sealed interface Message {
        data class UpdateSelectedPlaces(
            val selectedPlaces: SelectedPlaces,
            val placesWithState: List<List<PlaceWithState>>,
            val places: List<List<Schedule.Place>>? = null,
        ) : Message
    }

}