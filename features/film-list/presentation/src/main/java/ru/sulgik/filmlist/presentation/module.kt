package ru.sulgik.filmlist.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmListPresentationModule = module {
    singleOf(::FilmListStoreImpl) bind FilmListStore::class
}