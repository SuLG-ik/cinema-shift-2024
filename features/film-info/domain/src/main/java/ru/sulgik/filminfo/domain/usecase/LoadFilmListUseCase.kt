package ru.sulgik.filminfo.domain.usecase

import ru.sulgik.filminfo.data.RemoteFilm
import ru.sulgik.filminfo.data.RemoteFilmInfoRepository
import ru.sulgik.filminfo.domain.converter.RemoteFilmConverter
import ru.sulgik.filminfo.domain.entity.Film

class LoadFilmInfoUseCase(
    private val remoteFilmInfoRepository: RemoteFilmInfoRepository,
    private val remoteFilmConverter: RemoteFilmConverter,
) {

    suspend operator fun invoke(filmId: String): Film {
        val film = remoteFilmInfoRepository.getFilmById(filmId)
        return remoteFilmConverter.convert(film)
    }

}
