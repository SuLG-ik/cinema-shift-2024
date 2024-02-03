package ru.sulgik.tickets.domain.usecase

import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.repository.FilmInfoRepository

class LoadFilmScheduleUseCase(
    private val filmInfoRepository: FilmInfoRepository,
) {
    suspend operator fun invoke(filmId: String): List<Schedule> {
        return filmInfoRepository.getFilmScheduleById(filmId)
    }

}
