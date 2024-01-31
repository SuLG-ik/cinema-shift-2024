package ru.sulgik.filminfo.presentation

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmInfoPresentationModule = module {
    singleOf(::FilmInfoStoreImpl) bind FilmInfoStore::class
}