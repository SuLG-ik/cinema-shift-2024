package ru.sulgik.tickets.places.domain.usecase

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces

class SelectPlaceUseCase {

    operator fun invoke(
        selectedPlaces: SelectedPlaces,
        place: Schedule.Place,
    ): SelectedPlaces {
        val selectedPlace = SelectedPlaces.Place(
            row = place.position.row,
            column = place.position.column,
            price = place.price,
        )
        val places = selectedPlaces.places + selectedPlace
        return SelectedPlaces(
            totalCost = places.sumOf { it.price },
            places = places,
        )
    }

}