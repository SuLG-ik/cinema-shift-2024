package ru.sulgik.tickets.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ticketsDataModule = module {
    singleOf(::GraphQLRemoteFilmInfoRepository) bind RemoteFilmInfoRepository::class
    singleOf(::GraphQLConverter)
}