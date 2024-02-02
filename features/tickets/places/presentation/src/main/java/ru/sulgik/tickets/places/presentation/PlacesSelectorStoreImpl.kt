package ru.sulgik.tickets.places.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher

@OptIn(ExperimentalMviKotlinApi::class)
class PlacesSelectorStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
) : PlacesSelectorStore,
    Store<PlacesSelectorStore.Intent, PlacesSelectorStore.State, PlacesSelectorStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "FilmScheduleStoreImpl",
        initialState = PlacesSelectorStore.State(),
        reducer = {
            when (it) {
                is Message.UpdateSelectedPlaces -> copy(
                    isContinueAvailable = it.places.isNotEmpty(),
                    selectedPlaces = it.places,
                    totalCost = it.places.sumOf { it.price }
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<PlacesSelectorStore.Intent.Select> { intent ->
                val selectedPlaces = state().selectedPlaces
                dispatch(
                    Message.UpdateSelectedPlaces(
                        selectedPlaces +
                                PlacesSelectorStore.State.SelectedPlace(
                                    intent.row,
                                    intent.column,
                                    intent.cost,
                                )
                    )
                )
            }
            onIntent<PlacesSelectorStore.Intent.Unselect> { intent ->
                val selectedPlaces = state().selectedPlaces
                dispatch(
                    Message.UpdateSelectedPlaces(
                        selectedPlaces.filterNot { it.row == intent.row && it.column == intent.column }
                    )
                )
            }
        },
    ) {

    sealed interface Message {
        data class UpdateSelectedPlaces(
            val places: List<PlacesSelectorStore.State.SelectedPlace>
        ) : Message
    }
}