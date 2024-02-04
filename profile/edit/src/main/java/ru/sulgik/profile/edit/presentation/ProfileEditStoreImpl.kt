package ru.sulgik.profile.edit.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sulgik.profile.edit.domain.entity.UserProfile
import ru.sulgik.profile.edit.domain.entity.UserProfileInput
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

@OptIn(ExperimentalMviKotlinApi::class)
class ProfileEditStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    loadProfileInfoUseCase: LoadProfileInfoUseCase,
    validateFirstNameUseCase: ValidateFirstNameUseCase,
    validateLastNameUseCase: ValidateLastNameUseCase,
    validateMiddleNameUseCase: ValidateMiddleNameUseCase,
    validateEmailUseCase: ValidateEmailUseCase,
    validateCityUseCase: ValidateCityUseCase,
    formatFirstNameUseCase: FormatFirstNameUseCase,
    formatMiddleNameUseCase: FormatMiddleNameUseCase,
    formatLastNameUseCase: FormatLastNameUseCase,
    formatEmailUseCase: FormatEmailUseCase,
    formatCityUseCase: FormatCityUseCase,
    isContinueAvailableUseCase: IsContinueAvailableUseCase,
    editProfileInfoUseCase: EditProfileInfoUseCase,
) : ProfileEditStore,
    Store<ProfileEditStore.Intent, ProfileEditStore.State, ProfileEditStore.Label> by storeFactory.create<_, Action, Message, _, _>(
        name = "ProfileEditStoreImpl",
        initialState = ProfileEditStore.State(),
        reducer = {
            when (it) {
                is Message.SetProfile -> copy(
                    isLoading = false,
                    profile = UserProfileInput(
                        firstName = UserProfileInput.EditableField(it.userProfile.firstName, null),
                        lastName = UserProfileInput.EditableField(it.userProfile.lastName, null),
                        middleName = UserProfileInput.EditableField(
                            it.userProfile.middleName,
                            null
                        ),
                        city = UserProfileInput.EditableField(it.userProfile.city, null),
                        phone = UserProfileInput.PhoneField(it.userProfile.phone),
                        email = UserProfileInput.EditableField(it.userProfile.email, null),
                    )
                )

                is Message.CityInput -> {
                    val profile = profile?.copy(
                        city = it.value,
                    )
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        profile = profile,
                    )
                }

                is Message.EmailInput -> {
                    val profile = profile?.copy(
                        email = it.value
                    )
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        profile = profile,
                    )
                }

                is Message.FirstNameInput -> {
                    val profile = profile?.copy(
                        firstName = it.value
                    )
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        profile = profile,
                    )
                }

                is Message.LastNameInput -> {
                    val profile = profile?.copy(
                        lastName = it.value,
                    )
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        profile = profile,
                    )
                }

                is Message.MiddleNameInput -> {
                    val profile = profile?.copy(
                        middleName = it.value,
                    )
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        profile = profile,
                    )
                }

                Message.Loading -> {
                    copy(
                        isContinueAvailable = false,
                        isLoading = true
                    )
                }

                Message.Saved ->
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(profile),
                        isLoading = false,
                    )
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) { dispatch(Action.Setup) },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onAction<Action.Setup> {
                launch {
                    try {
                        val profile = loadProfileInfoUseCase()
                        withContext(Dispatchers.Main) {
                            dispatch(Message.SetProfile(profile))
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            publish(ProfileEditStore.Label.Unauthorized)
                        }
                    }
                }
            }
            onIntent<ProfileEditStore.Intent.Save> {
                if (state().isLoading)
                    return@onIntent
                val profile = state().profile ?: return@onIntent
                dispatch(Message.Loading)
                launch {
                    try {
                        editProfileInfoUseCase(
                            UserProfile(
                                firstName = profile.firstName.value,
                                lastName = profile.lastName.value,
                                middleName = profile.middleName.value,
                                email = profile.email.value,
                                city = profile.city.value,
                                phone = profile.phone.value
                            )
                        )
                        withContext(Dispatchers.Main) {
                            dispatch(Message.Saved)
                        }
                    } catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            dispatch(Message.Saved)
                        }
                    }
                }
            }
            onIntent<ProfileEditStore.Intent.FirstNameInput> {
                if (state().isLoading)
                    return@onIntent
                val formatted = formatFirstNameUseCase(it.value)
                dispatch(Message.FirstNameInput(validateFirstNameUseCase(formatted)))
            }
            onIntent<ProfileEditStore.Intent.LastNameInput> {
                if (state().isLoading)
                    return@onIntent
                val formatted = formatLastNameUseCase(it.value)
                dispatch(Message.LastNameInput(validateLastNameUseCase(formatted)))
            }
            onIntent<ProfileEditStore.Intent.MiddleNameInput> {
                if (state().isLoading)
                    return@onIntent
                val formatted = formatMiddleNameUseCase(it.value)
                dispatch(Message.MiddleNameInput(validateMiddleNameUseCase(formatted)))
            }
            onIntent<ProfileEditStore.Intent.CityInput> {
                if (state().isLoading)
                    return@onIntent
                val formatted = formatCityUseCase(it.value)
                dispatch(Message.CityInput(validateCityUseCase(formatted)))
            }
            onIntent<ProfileEditStore.Intent.EmailInput> {
                if (state().isLoading)
                    return@onIntent
                val formatted = formatEmailUseCase(it.value)
                dispatch(Message.EmailInput(validateEmailUseCase(formatted)))
            }
        },
    ) {

    sealed interface Action {
        data object Setup : Action
    }

    sealed interface Message {
        data object Loading : Message
        data object Saved : Message
        data class SetProfile(
            val userProfile: UserProfile,
        ) : Message

        data class FirstNameInput(val value: UserProfileInput.EditableField) : Message
        data class LastNameInput(val value: UserProfileInput.EditableField) : Message
        data class MiddleNameInput(val value: UserProfileInput.EditableField) : Message
        data class EmailInput(val value: UserProfileInput.EditableField) : Message
        data class CityInput(val value: UserProfileInput.EditableField) : Message

    }
}