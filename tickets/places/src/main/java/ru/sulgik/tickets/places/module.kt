package ru.sulgik.tickets.places

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.tickets.places.domain.usecase.FindPlacesBySeanceInScheduleUseCase
import ru.sulgik.tickets.places.domain.usecase.MakePlaceWithStateUseCase
import ru.sulgik.tickets.places.domain.usecase.SelectPlaceUseCase
import ru.sulgik.tickets.places.domain.usecase.UnselectPlaceUseCase
import ru.sulgik.tickets.places.presentation.PlacesSelectorStore
import ru.sulgik.tickets.places.presentation.PlacesSelectorStoreImpl

val ticketsPlacesSelectorModule = module {
    factoryOf(::PlacesSelectorStoreImpl) bind PlacesSelectorStore::class
    factoryOf(::SelectPlaceUseCase)
    factoryOf(::UnselectPlaceUseCase)
    factoryOf(::FindPlacesBySeanceInScheduleUseCase)
    factoryOf(::MakePlaceWithStateUseCase)
}