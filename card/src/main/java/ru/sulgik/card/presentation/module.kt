package ru.sulgik.card.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cardInputPresentationModule = module {
    factoryOf(::CardInputStoreImpl) bind CardInputStore::class
}