package ru.sulgik.tickets.places.domain.usecase

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces

class UnselectPlaceUseCase {

    operator fun invoke(
        selectedPlaces: SelectedPlaces,
        place: Schedule.Place,
    ): SelectedPlaces {
        val places =
            selectedPlaces.places.filterNot { it.row == place.position.row && it.column == place.position.column }
        return SelectedPlaces(
            totalCost = places.sumOf { it.price },
            places = places,
        )
    }

}