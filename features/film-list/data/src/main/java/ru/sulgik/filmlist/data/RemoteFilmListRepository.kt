package ru.sulgik.filmlist.data

interface RemoteFilmListRepository {

    suspend fun getAllFilms(): List<RemoteFilm>

}