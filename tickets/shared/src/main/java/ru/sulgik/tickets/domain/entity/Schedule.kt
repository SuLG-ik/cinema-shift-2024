package ru.sulgik.tickets.domain.entity

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Schedule(
    val date: LocalDate,
    val seances: List<Seance>
) {
    data class Seance(
        val date: LocalDate,
        val time: LocalTime,
        val hall: Hall,
        val payedTickets: List<PayedTicket>,
    )

    enum class HallType {
        RED, GREEN, BLUE, UNKNOWN,
    }

    data class Hall(
        val name: String,
        val type: HallType,
        val places: List<List<Place>>,
    )

    data class Place(
        val price: Int,
        val position: PlacePosition,
        val type: FilmHallCellType,
    )

    data class PlacePosition(
        val row: Int,
        val column: Int,
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