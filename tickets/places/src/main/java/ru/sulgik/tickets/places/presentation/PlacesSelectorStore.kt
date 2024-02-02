package ru.sulgik.tickets.places.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.places.domain.entity.PlaceWithState

interface PlacesSelectorStore :
    Store<PlacesSelectorStore.Intent, PlacesSelectorStore.State, PlacesSelectorStore.Label> {

    sealed interface Intent {

        data class UpdatePlaceList(
            val seance: SelectedSeance,
            val schedule: List<Schedule>,
        ) : Intent

        data class Select(
            val place: PlaceWithState
        ) : Intent


    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val selectedPlaces: SelectedPlaces = SelectedPlaces(0, emptyList()),
        val placeWithState: List<List<PlaceWithState>> = emptyList(),
        val places: List<List<Schedule.Place>> = emptyList(),
    )

    sealed interface Label

}