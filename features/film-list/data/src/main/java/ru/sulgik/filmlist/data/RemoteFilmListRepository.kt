package ru.sulgik.filmlist.data

interface RemoteFilmListRepository {

    fun getAllFilms(): List<RemoteFilm>

}