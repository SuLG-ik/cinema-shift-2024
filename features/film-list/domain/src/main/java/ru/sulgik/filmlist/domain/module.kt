package ru.sulgik.filmlist.domain

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ru.sulgik.filmlist.domain.usecase.LoadFilmListUseCase

val filmListDomainModule = module {
    factoryOf(::LoadFilmListUseCase)
}