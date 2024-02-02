package ru.sulgik.tickets.data

import java.time.LocalDate
import java.time.LocalTime

data class RemoteSchedule(
    val date: LocalDate,
    val seances: List<Seance>
) {
    data class Seance(
        val time: LocalTime,
        val hall: Hall,
        val payedTickets: List<PayedTicket>,
    )

    data class Hall(
        val name: String,
        val places: List<List<Place>>,
    )

    data class Place(
        val price: Int,
        val type: FilmHallCellType,
    )

    data class PayedTicket(
        val column: Int,
        val filmId: String,
        val phone: String,
        val row: Int,
    )

    enum class FilmHallCellType {
        BLOCKED, COMFORT, ECONOM
    }

}