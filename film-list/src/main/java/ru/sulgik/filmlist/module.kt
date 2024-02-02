package ru.sulgik.filmlist

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.filmlist.data.GraphQLFilmListRepository
import ru.sulgik.filmlist.data.converter.RemoteFilmConverter
import ru.sulgik.filmlist.domain.repository.FilmListRepository
import ru.sulgik.filmlist.domain.usecase.LoadFilmListUseCase
import ru.sulgik.filmlist.presentation.FilmListStore
import ru.sulgik.filmlist.presentation.FilmListStoreImpl

val filmListModule = module {
    singleOf(::RemoteFilmConverter)
    factoryOf(::LoadFilmListUseCase)
    singleOf(::GraphQLFilmListRepository) bind FilmListRepository::class
    singleOf(::FilmListStoreImpl) bind FilmListStore::class
}