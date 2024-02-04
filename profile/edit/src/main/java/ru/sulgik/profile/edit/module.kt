package ru.sulgik.profile.edit

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.sulgik.profile.edit.data.converter.KtorUserConverter
import ru.sulgik.profile.edit.data.repository.KtorUserProfileRepository
import ru.sulgik.profile.edit.domain.repository.UserProfileRepository
import ru.sulgik.profile.edit.domain.usecase.EditProfileInfoUseCase
import ru.sulgik.profile.edit.domain.usecase.FormatCityUseCase
import ru.sulgik.profile.edit.domain.usecase.FormatEmailUseCase
import ru.sulgik.profile.edit.domain.usecase.FormatFirstNameUseCase
import ru.sulgik.profile.edit.domain.usecase.FormatLastNameUseCase
import ru.sulgik.profile.edit.domain.usecase.FormatMiddleNameUseCase
import ru.sulgik.profile.edit.domain.usecase.IsContinueAvailableUseCase
import ru.sulgik.profile.edit.domain.usecase.LoadProfileInfoUseCase
import ru.sulgik.profile.edit.domain.usecase.ValidateCityUseCase
import ru.sulgik.profile.edit.domain.usecase.ValidateEmailUseCase
import ru.sulgik.profile.edit.domain.usecase.ValidateFirstNameUseCase
import ru.sulgik.profile.edit.domain.usecase.ValidateLastNameUseCase
import ru.sulgik.profile.edit.domain.usecase.ValidateMiddleNameUseCase
import ru.sulgik.profile.edit.presentation.ProfileEditStore
import ru.sulgik.profile.edit.presentation.ProfileEditStoreImpl

val profileEditModule = module {
    factoryOf(::ProfileEditStoreImpl) bind ProfileEditStore::class
    factoryOf(::LoadProfileInfoUseCase)
    singleOf(::KtorUserProfileRepository) bind UserProfileRepository::class
    factoryOf(::KtorUserConverter)
    factoryOf(::FormatEmailUseCase)
    factoryOf(::FormatFirstNameUseCase)
    factoryOf(::FormatMiddleNameUseCase)
    factoryOf(::FormatLastNameUseCase)
    factoryOf(::FormatCityUseCase)
    factoryOf(::ValidateCityUseCase)
    factoryOf(::ValidateEmailUseCase)
    factoryOf(::ValidateMiddleNameUseCase)
    factoryOf(::ValidateFirstNameUseCase)
    factoryOf(::ValidateLastNameUseCase)
    factoryOf(::IsContinueAvailableUseCase)
    factoryOf(::EditProfileInfoUseCase)
}