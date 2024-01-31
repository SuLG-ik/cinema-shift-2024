package ru.sulgik.filminfo.data

interface RemoteFilmInfoRepository {

    suspend fun getFilmById(id: String): RemoteFilm

}