package ru.sulgik.tickets.places.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.tickets.places.presentation.PlacesSelectorStore
import ru.sulgik.tickets.places.presentation.PlacesSelectorStoreImpl

val placesSelectorPresentationModule = module {
    factoryOf(::PlacesSelectorStoreImpl) bind PlacesSelectorStore::class
}