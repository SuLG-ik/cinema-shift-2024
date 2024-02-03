package ru.sulgik.filmlist.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.filmlist.domain.repository.FilmListRepository

val filmListDataModule = module {
    singleOf(::GraphQLFilmListRepository) bind FilmListRepository::class
}