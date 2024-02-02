package ru.sulgik.tickets.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sulgik.tickets.domain.usecase.LoadFilmScheduleUseCase
import ru.sulgik.tickets.domain.converter.RemoteFilmConverter
import ru.sulgik.tickets.domain.usecase.LoadShortFilmUseCase

val ticketsDomainModule = module {
    singleOf(::RemoteFilmConverter)
    factoryOf(::LoadFilmScheduleUseCase)
    factoryOf(::LoadShortFilmUseCase)
}