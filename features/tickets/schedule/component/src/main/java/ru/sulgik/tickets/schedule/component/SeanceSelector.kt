package ru.sulgik.tickets.schedule.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.tickets.domain.entity.Schedule
import java.time.LocalDate
import java.time.LocalTime


interface SeanceSelector {

    val state: Value<State>

    fun onSelect(date: LocalDate, time: LocalTime, hall: State.Schedule.Hall)

    fun onBack()

    fun onContinue()

    data class SelectedSeance(
        val date: LocalDate,
        val time: LocalTime,
        val hallType: Schedule.HallType,
    )

    data class State(
        val isLoading: Boolean,
        val isContinueAvailable: Boolean,
        val schedule: List<Schedule>?,
    ) {
        data class Schedule(
            val date: LocalDate,
            val seances: List<Seance>
        ) {
            data class Seance(
                val hall: Hall,
                val time: LocalTime,
                val isSelected: Boolean,
            )

            enum class Hall {
                RED, GREEN, BLUE, SIMPLE
            }
        }
    }
}