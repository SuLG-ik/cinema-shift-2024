package ru.sulgik.core.auth

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.core.auth.repository.DataStoreLocalAuthRepository
import ru.sulgik.core.auth.repository.LocalAuthRepository

val localAuthModule = module {
    singleOf(::DataStoreLocalAuthRepository) bind LocalAuthRepository::class
}