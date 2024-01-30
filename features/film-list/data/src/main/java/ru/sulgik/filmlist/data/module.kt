package ru.sulgik.filmlist.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmListDataModule = module {
    singleOf(::GraphQLRemoteFilmListRepository) bind RemoteFilmListRepository::class
}