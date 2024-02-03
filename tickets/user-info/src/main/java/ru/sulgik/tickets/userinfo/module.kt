package ru.sulgik.tickets.userinfo

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.tickets.userinfo.domain.usecase.FormatFirstNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatLastNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatMiddleNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatPhoneUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.IsContinueAvailableUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateFirstNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateLastNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateMiddleNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidatePhoneUseCase
import ru.sulgik.tickets.userinfo.presentation.UserInfoInputStore
import ru.sulgik.tickets.userinfo.presentation.UserInfoInputStoreImpl

val ticketsUserInfoModule = module {
    factoryOf(::UserInfoInputStoreImpl) bind UserInfoInputStore::class
    factoryOf(::FormatFirstNameUseCase)
    factoryOf(::FormatLastNameUseCase)
    factoryOf(::FormatMiddleNameUseCase)
    factoryOf(::FormatPhoneUseCase)
    factoryOf(::ValidateFirstNameUseCase)
    factoryOf(::ValidateLastNameUseCase)
    factoryOf(::ValidateMiddleNameUseCase)
    factoryOf(::ValidatePhoneUseCase)
    factoryOf(::IsContinueAvailableUseCase)
}