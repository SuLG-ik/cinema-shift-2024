package ru.sulgik.tickets.userinfo.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import ru.sulgik.core.validation.user.UserFirstNameValidationResult
import ru.sulgik.core.validation.user.UserInputFormatter
import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.core.validation.user.UserLastNameValidationResult
import ru.sulgik.core.validation.user.UserMiddleNameValidationResult
import ru.sulgik.core.validation.user.UserPhoneValidationResult

@OptIn(ExperimentalMviKotlinApi::class)
internal class UserInfoInputStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    validator: UserInputValidator,
    formatter: UserInputFormatter
) : UserInfoInputStore,
    Store<UserInfoInputStore.Intent, UserInfoInputStore.State, UserInfoInputStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "UserInfoInputStoreImpl",
        initialState = UserInfoInputStore.State(),
        reducer = {
            when (it) {
                is Message.FirstNameField -> copy(
                    userInfo = userInfo.copy(
                        firstName = UserInfoInputStore.State.UserInfo.FirstNameField(
                            value = it.result.value,
                        )
                    )
                )

                is Message.LastNameField -> copy(
                    userInfo = userInfo.copy(
                        lastName = UserInfoInputStore.State.UserInfo.LastNameField(
                            value = it.result.value,
                        )
                    )
                )

                is Message.MiddleNameField -> copy(
                    userInfo = userInfo.copy(
                        middleName = UserInfoInputStore.State.UserInfo.MiddleNameField(
                            value = it.result.value,
                        )
                    )
                )

                is Message.PhoneField -> copy(
                    userInfo = userInfo.copy(
                        phone = UserInfoInputStore.State.UserInfo.PhoneField(
                            value = it.result.value,
                        )
                    )
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<UserInfoInputStore.Intent.FirstNameInput> {
                val formatted = formatter.formatFirstName(it.value)
                val validationResult = validator.validateFirstName(formatted)
                dispatch(Message.FirstNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.LastNameInput> {
                val formatted = formatter.formatLastName(it.value)
                val validationResult = validator.validateLastName(formatted)
                dispatch(Message.LastNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.MiddleNameInput> {
                val formatted = formatter.formatMiddleName(it.value)
                val validationResult = validator.validateMiddleName(formatted)
                dispatch(Message.MiddleNameField(validationResult))
            }
            onIntent<UserInfoInputStore.Intent.PhoneInput> {
                if (!state().userInfo.phone.isEditable)
                    return@onIntent
                val formatted = formatter.formatPhone(it.value)
                val validationResult = validator.validatePhone(formatted)
                dispatch(Message.PhoneField(validationResult))
            }
        },
    ) {

    sealed interface Message {
        data class FirstNameField(
            val result: UserFirstNameValidationResult,
        ) : Message

        data class LastNameField(
            val result: UserLastNameValidationResult,
        ) : Message

        data class MiddleNameField(
            val result: UserMiddleNameValidationResult,
        ) : Message

        data class PhoneField(
            val result: UserPhoneValidationResult
        ) : Message
    }
}