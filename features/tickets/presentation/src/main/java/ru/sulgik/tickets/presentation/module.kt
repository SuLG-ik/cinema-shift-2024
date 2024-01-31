package ru.sulgik.tickets.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ticketsPresentationModule = module {
    singleOf(::FilmScheduleStoreImpl) bind FilmScheduleStore::class
}