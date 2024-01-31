package ru.sulgik.tickets.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ticketsPresentationModule = module {
    factoryOf(::FilmScheduleStoreImpl) bind FilmScheduleStore::class
}