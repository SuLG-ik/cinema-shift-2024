package ru.sulgik.tickets.domain.converter

import ru.sulgik.tickets.data.RemoteFilm
import ru.sulgik.tickets.data.RemoteSchedule
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule

class RemoteFilmConverter {
    fun convert(remoteFilm: RemoteSchedule): Schedule {
        return Schedule(
            date = remoteFilm.date, seances = remoteFilm.seances.map { it.convert() }
        )
    }

    fun convert(remoteFilm: List<RemoteSchedule>): List<Schedule> {
        return remoteFilm.map { convert(it) }
    }

    fun convert(remoteFilm: RemoteFilm): Film {
        return Film(remoteFilm.title)
    }

}


private fun RemoteSchedule.Seance.convert(): Schedule.Seance {
    val isSelectedTickets = List(hall.places.size) { row ->
        List(hall.places[row].size) { column ->
            payedTickets.any { it.column == column + 1 && it.row == row + 1 }
        }
    }
    return Schedule.Seance(
        time = time, hall = hall.convert(isSelectedTickets), payedTickets = payedTickets.map { it.convert() }

    )
}

private fun RemoteSchedule.PayedTicket.convert(): Schedule.PayedTicket {
    return Schedule.PayedTicket(
        column = column, filmId = filmId, phone = phone, row = row
    )
}

private fun RemoteSchedule.Hall.convert(isSelectedTickets: List<List<Boolean>>): Schedule.Hall {
    return Schedule.Hall(
        name = name,
        places = places.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, column ->
                column.convert()
            }
        }
    )
}

private fun RemoteSchedule.Place.convert(): Schedule.Place {
    return Schedule.Place(
        price = price,
        type = type.convert(),
    )
}

private fun RemoteSchedule.FilmHallCellType.convert(): Schedule.FilmHallCellType {
    return when (this) {
        RemoteSchedule.FilmHallCellType.BLOCKED -> Schedule.FilmHallCellType.BLOCKED
        RemoteSchedule.FilmHallCellType.COMFORT -> Schedule.FilmHallCellType.COMPORT
        RemoteSchedule.FilmHallCellType.ECONOM -> Schedule.FilmHallCellType.ECONOM
    }
}

