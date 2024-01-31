package ru.sulgik.tickets.data

interface RemoteFilmInfoRepository {

    suspend fun getFilmScheduleById(id: String): List<RemoteSchedule>

}