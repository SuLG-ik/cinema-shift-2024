package ru.sulgik.filminfo.domain.usecase

import ru.sulgik.filminfo.domain.entity.Film
import ru.sulgik.filminfo.domain.repository.FilmInfoRepository

class LoadFilmInfoUseCase(
    private val filmInfoRepository: FilmInfoRepository,
) {

    suspend operator fun invoke(filmId: String): Film {
        return filmInfoRepository.getFilmById(filmId)
    }

}
