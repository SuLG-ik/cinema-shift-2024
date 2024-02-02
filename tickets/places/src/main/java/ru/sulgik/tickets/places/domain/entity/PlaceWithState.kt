package ru.sulgik.tickets.places.domain.entity

import ru.sulgik.tickets.domain.entity.Schedule

class PlaceWithState(
    val place: Schedule.Place,
    val state: State,
) {

    enum class State {
        BLOCKED, SELECTED, UNSELECTED
    }

}