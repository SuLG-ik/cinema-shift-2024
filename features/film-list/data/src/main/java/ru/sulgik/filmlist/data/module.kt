package ru.sulgik.filmlist.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmListModule = module {
    singleOf(::GraphQLRemoteFilmListRepository) bind RemoteFilmListRepository::class
}