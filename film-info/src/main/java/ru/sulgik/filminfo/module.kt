package ru.sulgik.filminfo

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.filminfo.data.GraphQLConverter
import ru.sulgik.filminfo.data.GraphQLFilmInfoRepository
import ru.sulgik.filminfo.domain.repository.FilmInfoRepository
import ru.sulgik.filminfo.domain.usecase.LoadFilmInfoUseCase
import ru.sulgik.filminfo.presentation.FilmInfoStore
import ru.sulgik.filminfo.presentation.FilmInfoStoreImpl

val filmInfoModule = module {
    singleOf(::GraphQLFilmInfoRepository) bind FilmInfoRepository::class
    singleOf(::GraphQLConverter)
    factoryOf(::LoadFilmInfoUseCase)
    factoryOf(::FilmInfoStoreImpl) bind FilmInfoStore::class
}