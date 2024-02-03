package ru.sulgik.tickets.schedule.domain

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.domain.entity.toSelectedHallType

class SelectSeanceUseCase {

    operator fun invoke(seance: Schedule.Seance): SelectedSeance {
        return SelectedSeance(
            seance.date,
            seance.time,
            seance.hall.type.toSelectedHallType(),
        )
    }

}