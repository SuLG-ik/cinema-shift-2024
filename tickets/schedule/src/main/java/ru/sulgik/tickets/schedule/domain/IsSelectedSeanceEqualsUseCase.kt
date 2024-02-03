package ru.sulgik.tickets.schedule.domain

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.domain.entity.toScheduleHallType

class IsSelectedSeanceEqualsUseCase {


    operator fun invoke(selectedSeance: SelectedSeance, seance: Schedule.Seance): Boolean {
        return selectedSeance.date == seance.date && selectedSeance.time == seance.time
                && selectedSeance.hallType.toScheduleHallType() == seance.hall.type
    }

}