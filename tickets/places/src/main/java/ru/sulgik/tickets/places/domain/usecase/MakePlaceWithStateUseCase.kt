package ru.sulgik.tickets.places.domain.usecase

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.places.domain.entity.PlaceWithState

class MakePlaceWithStateUseCase {

    operator fun invoke(
        places: List<List<Schedule.Place>>,
        selectedPlaces: List<SelectedPlaces.Place>
    ): List<List<PlaceWithState>> {
        return places.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, place ->
                PlaceWithState(
                    place = place,
                    state = convertToType(
                        isExternalSelected = place.type == Schedule.FilmHallCellType.BLOCKED,
                        isOrderSelected = selectedPlaces.any { it.column == columnIndex + 1 && it.row == rowIndex + 1 },
                    )
                )
            }
        }
    }

    private fun convertToType(
        isExternalSelected: Boolean,
        isOrderSelected: Boolean
    ): PlaceWithState.State {
        return when {
            isExternalSelected -> PlaceWithState.State.BLOCKED
            isOrderSelected -> PlaceWithState.State.SELECTED
            else -> PlaceWithState.State.UNSELECTED
        }
    }

}