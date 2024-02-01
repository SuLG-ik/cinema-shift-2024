package ru.sulgik.tickets.domain.usecase

import ru.sulgik.tickets.data.RemoteFilmInfoRepository
import ru.sulgik.tickets.domain.converter.RemoteFilmConverter
import ru.sulgik.tickets.domain.entity.Film

class LoadShortFilmUseCase(
    private val remoteFilmInfoRepository: RemoteFilmInfoRepository,
    private val remoteFilmConverter: RemoteFilmConverter,
) {
    suspend operator fun invoke(filmId: String): Film {
        return remoteFilmConverter.convert(remoteFilmInfoRepository.getShortFilm(filmId))
    }

}
