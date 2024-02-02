package ru.sulgik.tickets.domain.usecase

import ru.sulgik.tickets.domain.repository.FilmInfoRepository
import ru.sulgik.tickets.domain.entity.Film

class LoadShortFilmUseCase(
    private val filmInfoRepository: FilmInfoRepository,
) {
    suspend operator fun invoke(filmId: String): Film {
        return filmInfoRepository.getShortFilm(filmId)
    }

}
