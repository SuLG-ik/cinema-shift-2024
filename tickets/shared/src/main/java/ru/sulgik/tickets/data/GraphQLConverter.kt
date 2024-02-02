package ru.sulgik.tickets.data

import kotlinx.datetime.LocalDate
import ru.sulgik.core.datetime.DateTimeFormatter
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule
import tickets.GetFilmScheduleQuery
import tickets.GetFilmTitleQuery
import type.FilmHallCellType


internal class GraphQLConverter(
    private val dateTimeFormatter: DateTimeFormatter,
) {

    fun convert(film: GetFilmTitleQuery.Film): Film {
        return Film(
            film.name
        )
    }

    private fun convert(schedule: GetFilmScheduleQuery.Schedule): Schedule {
        val date = dateTimeFormatter.parseDate(schedule.date)
        return Schedule(
            date = date,
            seances = schedule.seances.map { it.convert(date) }
        )
    }

    fun convert(schedule: List<GetFilmScheduleQuery.Schedule>): List<Schedule> {
        return schedule.map { convert(it) }
    }

    private fun GetFilmScheduleQuery.Seance.convert(date: LocalDate): Schedule.Seance {
        return Schedule.Seance(
            date = date,
            time = dateTimeFormatter.parseTime(time),
            hall = hall.convert(),
            payedTickets = payedTickets.map { it.convert() }
        )
    }

    private fun GetFilmScheduleQuery.PayedTicket.convert(): Schedule.PayedTicket {
        return Schedule.PayedTicket(
            column = column.toInt(), filmId = filmId, phone = phone, row = row.toInt()
        )
    }

    private fun GetFilmScheduleQuery.Hall.convert(): Schedule.Hall {
        return Schedule.Hall(
            name = name,
            type = name.toHallType(),
            places = places.mapIndexed { rowIndex, row ->
                row.mapIndexed { columnIndex, place ->
                    place.convert(rowIndex + 1, columnIndex + 1)
                }
            }

        )
    }

    private fun GetFilmScheduleQuery.Place.convert(row: Int, column: Int): Schedule.Place {
        return Schedule.Place(
            price = price.toInt(),
            position = Schedule.PlacePosition(
                row = row,
                column = column,
            ),
            type = type.convert(),
        )
    }

    private fun FilmHallCellType.convert(): Schedule.FilmHallCellType {
        return when (this) {
            FilmHallCellType.BLOCKED -> Schedule.FilmHallCellType.BLOCKED
            FilmHallCellType.COMFORT -> Schedule.FilmHallCellType.COMFORT
            FilmHallCellType.ECONOM -> Schedule.FilmHallCellType.ECONOM
            FilmHallCellType.UNKNOWN__ -> Schedule.FilmHallCellType.BLOCKED
        }
    }

}

private fun String.toHallType(): Schedule.HallType {
    return when (this) {
        "Red" -> Schedule.HallType.RED
        "Green" -> Schedule.HallType.GREEN
        "Blue" -> Schedule.HallType.BLUE
        else -> Schedule.HallType.UNKNOWN
    }
}
