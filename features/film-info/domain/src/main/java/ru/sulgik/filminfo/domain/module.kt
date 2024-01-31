package ru.sulgik.filminfo.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sulgik.filminfo.domain.usecase.LoadFilmInfoUseCase
import ru.sulgik.filminfo.domain.converter.RemoteFilmConverter

val filmInfoDomainModule = module {
    singleOf(::RemoteFilmConverter)
    factoryOf(::LoadFilmInfoUseCase)
}