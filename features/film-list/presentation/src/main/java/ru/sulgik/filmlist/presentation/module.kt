package ru.sulgik.filmlist.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmListPresentationModule = module {
    factoryOf(::FilmListStoreImpl) bind FilmListStore::class
}