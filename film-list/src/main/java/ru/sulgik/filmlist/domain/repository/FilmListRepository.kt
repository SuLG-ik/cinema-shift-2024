package ru.sulgik.filmlist.domain.repository

import ru.sulgik.filmlist.domain.entity.Film

interface FilmListRepository {

    suspend fun getAllFilms(): List<Film>

}