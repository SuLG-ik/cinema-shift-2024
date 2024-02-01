package ru.sulgik.tickets.data

interface RemoteFilmInfoRepository {

    suspend fun getFilmScheduleById(id: String): List<RemoteSchedule>

    suspend fun getShortFilm(id: String): RemoteFilm

}