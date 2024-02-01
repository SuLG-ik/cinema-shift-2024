package ru.sulgik.tickets.places.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.tickets.domain.entity.Schedule
import java.time.LocalDate
import java.time.LocalTime


interface PlacesSelector {

    val state: Value<State>

    fun onBack()

    fun onContinue()

    fun onSelect(row: Int, column: Int, price: Int)
    fun onUnselect(row: Int, column: Int)

    data class SelectedSeance(
        val date: LocalDate,
        val time: LocalTime,
        val hallType: Schedule.HallType,
    )

    data class SelectedPlaces(
        val totalCost: Int,
        val places: List<SelectedPlace>,
    )


    data class SelectedPlace(
        val row: Int,
        val column: Int,
    )


    data class State(
        val isLoading: Boolean,
        val isContinueAvailable: Boolean,
        val places: List<List<Place>>,
        val selectedPlaces: List<Place>,
        val totalCost: Int,
    ) {

        data class Place(
            val state: PlaceState,
            val cost: Int,
            val position: Position,
        )

        data class Position(
            val row: Int,
            val column: Int,
        )

        enum class PlaceState {
            UNSELECTED, EXTERNAL_SELECTED, SELECTED
        }

    }
}