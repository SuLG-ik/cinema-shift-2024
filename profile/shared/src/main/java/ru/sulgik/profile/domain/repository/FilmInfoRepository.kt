package ru.sulgik.profile.domain.repository

import ru.sulgik.profile.domain.entity.Film
import ru.sulgik.profile.domain.entity.Schedule

interface FilmInfoRepository {

    suspend fun getFilmScheduleById(id: String): List<Schedule>

    suspend fun getShortFilm(id: String): Film

}