package ru.sulgik.filminfo.data

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.filminfo.data.GraphQLRemoteFilmInfoRepository
import ru.sulgik.filminfo.data.RemoteFilmInfoRepository

val filmInfoDataModule = module {
    singleOf(::GraphQLRemoteFilmInfoRepository) bind RemoteFilmInfoRepository::class
}