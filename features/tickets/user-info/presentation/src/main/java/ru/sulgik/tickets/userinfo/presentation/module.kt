package ru.sulgik.tickets.userinfo.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userInfoInputPresentationModule = module {
    factoryOf(::UserInfoInputStoreImpl) bind UserInfoInputStore::class
}