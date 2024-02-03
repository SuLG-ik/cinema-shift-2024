package ru.sulgik.tickets.userinfo.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo
import ru.sulgik.tickets.userinfo.domain.usecase.FormatFirstNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatLastNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatMiddleNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.FormatPhoneUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.IsContinueAvailableUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateFirstNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateLastNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidateMiddleNameUseCase
import ru.sulgik.tickets.userinfo.domain.usecase.ValidatePhoneUseCase

@OptIn(ExperimentalMviKotlinApi::class)
internal class UserInfoInputStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    isContinueAvailableUseCase: IsContinueAvailableUseCase,
    formatFirstNameUseCase: FormatFirstNameUseCase,
    formatLastNameUseCase: FormatLastNameUseCase,
    formatMiddleNameUseCase: FormatMiddleNameUseCase,
    formatPhoneUseCase: FormatPhoneUseCase,
    validateFirstNameUseCase: ValidateFirstNameUseCase,
    validateLastNameUseCase: ValidateLastNameUseCase,
    validateMiddleNameUseCase: ValidateMiddleNameUseCase,
    validatePhoneUseCase: ValidatePhoneUseCase,
) : UserInfoInputStore,
    Store<UserInfoInputStore.Intent, UserInfoInputStore.State, UserInfoInputStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "UserInfoInputStoreImpl",
        initialState = UserInfoInputStore.State(),
        reducer = {
            when (it) {
                is Message.FirstNameField -> {
                    val userInfo = userInfo.copy(firstName = it.result)
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(userInfo),
                        userInfo = userInfo
                    )
                }

                is Message.LastNameField -> {
                    val userInfo = userInfo.copy(lastName = it.result)
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(userInfo),
                        userInfo = userInfo
                    )
                }

                is Message.MiddleNameField -> {
                    val userInfo = userInfo.copy(middleName = it.result)
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(userInfo),
                        userInfo = userInfo
                    )
                }

                is Message.PhoneField -> {
                    val userInfo = userInfo.copy(phone = it.result)
                    copy(
                        isContinueAvailable = isContinueAvailableUseCase(userInfo),
                        userInfo = userInfo
                    )
                }
            }
        },
        bootstrapper = coroutineBootstrapper(coroutineDispatcher) {},
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<UserInfoInputStore.Intent.FirstNameInput> {
                val formatted = formatFirstNameUseCase(it.value)
                val validationResult = validateFirstNameUseCase(formatted)
                dispatch(Message.FirstNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.LastNameInput> {
                val formatted = formatLastNameUseCase(it.value)
                val validationResult = validateLastNameUseCase(formatted)
                dispatch(Message.LastNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.MiddleNameInput> {
                val formatted = formatMiddleNameUseCase(it.value)
                val validationResult = validateMiddleNameUseCase(formatted)
                dispatch(Message.MiddleNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.PhoneInput> {
                if (!state().userInfo.phone.isEditable)
                    return@onIntent
                val formatted = formatPhoneUseCase(it.value)
                val validationResult = validatePhoneUseCase(formatted, true)
                dispatch(Message.PhoneField(validationResult))
            }
        },
    ) {

    sealed interface Message {
        data class FirstNameField(
            val result: UserInfo.FirstNameField,
        ) : Message

        data class LastNameField(
            val result: UserInfo.LastNameField,
        ) : Message

        data class MiddleNameField(
            val result: UserInfo.MiddleNameField,
        ) : Message

        data class PhoneField(
            val result: UserInfo.PhoneField,
        ) : Message
    }
}