package ru.sulgik.tickets.domain.repository

import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule

interface FilmInfoRepository {

    suspend fun getFilmScheduleById(id: String): List<Schedule>

    suspend fun getShortFilm(id: String): Film

}