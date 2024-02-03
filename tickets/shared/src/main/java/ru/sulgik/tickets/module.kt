package ru.sulgik.tickets

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.tickets.data.GraphQLConverter
import ru.sulgik.tickets.data.GraphQLFilmInfoRepository
import ru.sulgik.tickets.domain.repository.FilmInfoRepository
import ru.sulgik.tickets.domain.usecase.LoadFilmScheduleUseCase
import ru.sulgik.tickets.domain.usecase.LoadShortFilmUseCase
import ru.sulgik.tickets.presentation.FilmScheduleStore
import ru.sulgik.tickets.presentation.FilmScheduleStoreImpl

val ticketsSharedModule = module {
    singleOf(::GraphQLFilmInfoRepository) bind FilmInfoRepository::class
    factoryOf(::FilmScheduleStoreImpl) bind FilmScheduleStore::class
    factoryOf(::LoadFilmScheduleUseCase)
    factoryOf(::LoadShortFilmUseCase)
    factoryOf(::GraphQLConverter)
}