package ru.sulgik.filmlist.domain.usecase

import ru.sulgik.filmlist.domain.entity.Film
import ru.sulgik.filmlist.domain.repository.FilmListRepository

class LoadFilmListUseCase(
    private val filmListRepository: FilmListRepository,
) {

    suspend operator fun invoke(): List<Film> {
        return filmListRepository.getAllFilms()
    }

}
