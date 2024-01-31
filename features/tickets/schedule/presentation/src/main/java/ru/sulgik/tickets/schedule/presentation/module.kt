package ru.sulgik.tickets.schedule.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val seanceSelectorPresentationModule = module {
    factoryOf(::SeanceSelectorStoreImpl) bind SeanceSelectorStore::class
}