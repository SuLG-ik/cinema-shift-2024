package ru.sulgik.tickets.schedule

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.tickets.schedule.domain.IsSelectedSeanceEqualsUseCase
import ru.sulgik.tickets.schedule.domain.SelectSeanceUseCase
import ru.sulgik.tickets.schedule.presentation.SeanceSelectorStore
import ru.sulgik.tickets.schedule.presentation.SeanceSelectorStoreImpl

val ticketsSeanceSelectorModule = module {
    factoryOf(::SeanceSelectorStoreImpl) bind SeanceSelectorStore::class
    factoryOf(::IsSelectedSeanceEqualsUseCase)
    factoryOf(::SelectSeanceUseCase)
}