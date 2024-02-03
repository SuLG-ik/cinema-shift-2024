package ru.sulgik.tickets.domain.entity

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.serialization.Serializable

@Serializable
data class SelectedSeance(
    val date: LocalDate,
    val time: LocalTime,
    val hallType: HallType,
) {

    @Serializable
    enum class HallType {
        RED, GREEN, BLUE, UNKNOWN,
    }

}

fun SelectedSeance.HallType.toScheduleHallType(): Schedule.HallType {
    return when (this) {
        SelectedSeance.HallType.RED -> Schedule.HallType.RED
        SelectedSeance.HallType.GREEN -> Schedule.HallType.GREEN
        SelectedSeance.HallType.BLUE -> Schedule.HallType.BLUE
        SelectedSeance.HallType.UNKNOWN -> Schedule.HallType.UNKNOWN
    }
}

fun Schedule.HallType.toSelectedHallType(): SelectedSeance.HallType {
    return when (this) {
        Schedule.HallType.RED -> SelectedSeance.HallType.RED
        Schedule.HallType.GREEN -> SelectedSeance.HallType.GREEN
        Schedule.HallType.BLUE -> SelectedSeance.HallType.BLUE
        Schedule.HallType.UNKNOWN -> SelectedSeance.HallType.UNKNOWN
    }
}
