package ru.sulgik.filmlist.domain.usecase

import ru.sulgik.filmlist.data.RemoteFilmListRepository
import ru.sulgik.filmlist.domain.converter.RemoteFilmConverter
import ru.sulgik.filmlist.domain.entity.Film

class LoadFilmListUseCase(
    private val remoteFilmListRepository: RemoteFilmListRepository,
    private val remoteFilmConverter: RemoteFilmConverter,
) {

    suspend operator fun invoke(): List<Film> {
        return remoteFilmConverter.convert(remoteFilmListRepository.getAllFilms())
    }

}
