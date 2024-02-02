package ru.sulgik.login

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.login.domain.usecase.FormatCodeUseCase
import ru.sulgik.login.domain.usecase.FormatPhoneUseCase
import ru.sulgik.login.domain.usecase.SaveAuthScopeUseCase
import ru.sulgik.login.domain.usecase.SendCodeUseCase
import ru.sulgik.login.domain.usecase.SignInUseCase
import ru.sulgik.login.domain.usecase.ValidateCodeUseCase
import ru.sulgik.login.domain.usecase.ValidatePhoneUseCase
import ru.sulgik.login.presentation.LoginStore
import ru.sulgik.login.presentation.LoginStoreImpl

val loginModule = module {
    factoryOf(::LoginStoreImpl) bind LoginStore::class
    factoryOf(::SignInUseCase)
    factoryOf(:: FormatPhoneUseCase)
    factoryOf(::FormatCodeUseCase)
    factoryOf(::ValidatePhoneUseCase)
    factoryOf(::ValidateCodeUseCase)
    factoryOf(::SaveAuthScopeUseCase)
    factoryOf(::SendCodeUseCase)
}