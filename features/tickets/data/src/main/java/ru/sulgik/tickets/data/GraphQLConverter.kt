package ru.sulgik.tickets.data

import filmlist.GetFilmScheduleQuery
import filmlist.GetFilmTitleQuery
import ru.sulgik.core.datetime.DateTimeFormatter
import type.FilmHallCellType


class GraphQLConverter(
    private val dateTimeFormatter: DateTimeFormatter,
) {

    fun convert(film: GetFilmTitleQuery.Film): RemoteFilm {
        return RemoteFilm(
            film.name
        )
    }
    private fun convert(schedule: GetFilmScheduleQuery.Schedule): RemoteSchedule {
        return RemoteSchedule(
            date = dateTimeFormatter.parseDate(schedule.date), seances = schedule.seances.map { it.convert() }
        )
    }

    fun convert(schedule: List<GetFilmScheduleQuery.Schedule>): List<RemoteSchedule> {
        return schedule.map { convert(it) }
    }

    private fun GetFilmScheduleQuery.Seance.convert(): RemoteSchedule.Seance {
        return RemoteSchedule.Seance(
            time = dateTimeFormatter.parseTime(time),
            hall = hall.convert(),
            payedTickets = payedTickets.map{ it.convert() }

        )
    }


    private fun GetFilmScheduleQuery.PayedTicket.convert(): RemoteSchedule.PayedTicket {
        return RemoteSchedule.PayedTicket(
            column = column.toInt(), filmId = filmId, phone = phone, row = row.toInt()
        )
    }

    private fun GetFilmScheduleQuery.Hall.convert(): RemoteSchedule.Hall {
        return RemoteSchedule.Hall(
            name = name, places = places.map { it.map { it.convert() } }

        )
    }
    private fun GetFilmScheduleQuery.Place.convert(): RemoteSchedule.Place {
        return RemoteSchedule.Place(
            price = price.toInt(), type = type.convert(),
        )
    }

    private fun FilmHallCellType.convert(): RemoteSchedule.FilmHallCellType {
        return when (this) {
            FilmHallCellType.BLOCKED -> RemoteSchedule.FilmHallCellType.BLOCKED
            FilmHallCellType.COMFORT -> RemoteSchedule.FilmHallCellType.COMFORT
            FilmHallCellType.ECONOM -> RemoteSchedule.FilmHallCellType.ECONOM
            FilmHallCellType.UNKNOWN__ -> RemoteSchedule.FilmHallCellType.BLOCKED
        }
    }

}
