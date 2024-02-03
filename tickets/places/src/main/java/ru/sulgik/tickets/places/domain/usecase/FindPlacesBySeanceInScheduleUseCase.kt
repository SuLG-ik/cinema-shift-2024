package ru.sulgik.tickets.places.domain.usecase

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance

class FindPlacesBySeanceInScheduleUseCase {

    operator fun invoke(
        schedule: List<Schedule>,
        seance: SelectedSeance,
    ): List<List<Schedule.Place>> {

        val date = schedule.find { it.date == seance.date }
            ?: throw IllegalStateException("Date ${seance.date} for places selection not found")
        val time =
            date.seances.find { it.time == seance.time && it.hall.type == seance.hallType.convert() }
                ?: throw IllegalStateException("Date ${seance.date} for places selection not found")
        return time.hall.places
    }

}

private fun SelectedSeance.HallType.convert(): Schedule.HallType {
    return when (this) {
        SelectedSeance.HallType.RED -> Schedule.HallType.RED
        SelectedSeance.HallType.GREEN -> Schedule.HallType.GREEN
        SelectedSeance.HallType.BLUE -> Schedule.HallType.BLUE
        SelectedSeance.HallType.UNKNOWN -> Schedule.HallType.UNKNOWN
    }
}
