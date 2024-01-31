package ru.sulgik.tickets.domain.usecase

import ru.sulgik.tickets.data.RemoteFilmInfoRepository
import ru.sulgik.tickets.domain.converter.RemoteFilmConverter
import ru.sulgik.tickets.domain.entity.Schedule

class LoadFilmScheduleUseCase(
    private val remoteFilmInfoRepository: RemoteFilmInfoRepository,
    private val remoteFilmConverter: RemoteFilmConverter,
) {

    suspend operator fun invoke(filmId: String): List<Schedule> {
        return remoteFilmConverter.convert(remoteFilmInfoRepository.getFilmScheduleById(filmId))
    }

}
