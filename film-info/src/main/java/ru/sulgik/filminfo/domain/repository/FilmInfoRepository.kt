package ru.sulgik.filminfo.domain.repository

import ru.sulgik.filminfo.domain.entity.Film

interface FilmInfoRepository {

    suspend fun getFilmById(id: String): Film

}