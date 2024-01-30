package ru.sulgik.filmlist.domain.usecase

import ru.sulgik.filmlist.data.RemoteFilmListRepository
import ru.sulgik.filmlist.domain.converter.convert
import ru.sulgik.filmlist.domain.entity.Film

class LoadFilmListUseCase(
    private val remoteFilmListRepository: RemoteFilmListRepository,
) {

    suspend operator fun invoke(): List<Film> {
        return remoteFilmListRepository.getAllFilms().convert()
    }

}
