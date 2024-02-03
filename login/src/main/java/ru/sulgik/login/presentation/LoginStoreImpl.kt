package ru.sulgik.login.presentation

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.utils.ExperimentalMviKotlinApi
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sulgik.core.validation.user.UserInputError
import ru.sulgik.login.domain.entity.LoginInput
import ru.sulgik.login.domain.usecase.FormatCodeUseCase
import ru.sulgik.login.domain.usecase.FormatPhoneUseCase
import ru.sulgik.login.domain.usecase.IsContinueAvailableUseCase
import ru.sulgik.login.domain.usecase.SendCodeUseCase
import ru.sulgik.login.domain.usecase.SignInUseCase
import ru.sulgik.login.domain.usecase.ValidateCodeUseCase
import ru.sulgik.login.domain.usecase.ValidatePhoneUseCase

@OptIn(ExperimentalMviKotlinApi::class)
class LoginStoreImpl(
    coroutineDispatcher: CoroutineDispatcher,
    storeFactory: StoreFactory,
    signInUseCase: SignInUseCase,
    sendCodeUseCase: SendCodeUseCase,
    formatCodeUseCase: FormatCodeUseCase,
    formatPhoneUseCase: FormatPhoneUseCase,
    validateCodeUseCase: ValidateCodeUseCase,
    validatePhoneUseCase: ValidatePhoneUseCase,
    isContinueAvailableUseCase: IsContinueAvailableUseCase,
) : LoginStore,
    Store<LoginStore.Intent, LoginStore.State, LoginStore.Label> by storeFactory.create<_, _, Message, _, _>(
        name = "LoginStoreImpl",
        initialState = LoginStore.State(),
        reducer = {
            when (it) {
                is Message.ChangeStep -> {
                    val loginInput = loginInput.copy(
                        isContinueAvailable = false,
                        isLoading = false,
                        step = it.step,
                    )
                    copy(
                        loginInput = loginInput.copy(
                            isContinueAvailable = isContinueAvailableUseCase(
                                loginInput
                            )
                        )
                    )
                }

                is Message.CodeField -> {
                    val loginInput = loginInput.copy(
                        code = it.field
                    )
                    copy(
                        loginInput = loginInput.copy(
                            isContinueAvailable = isContinueAvailableUseCase(
                                loginInput
                            )
                        )
                    )
                }

                is Message.PhoneField -> {
                    val loginInput = loginInput.copy(
                        phone = it.field
                    )
                    copy(
                        loginInput = loginInput.copy(
                            isContinueAvailable = isContinueAvailableUseCase(
                                loginInput
                            )
                        )
                    )
                }

                Message.Loading -> {
                    copy(
                        loginInput = loginInput.copy(
                            isLoading = true,
                            isContinueAvailable = false,
                        )
                    )
                }

                Message.IllegalCode -> copy(
                    loginInput = loginInput.copy(
                        code = loginInput.code.copy(error = UserInputError.IncorrectInput),
                        isContinueAvailable = false,
                        isLoading = false,
                    ),
                )
            }
        },
        executorFactory = coroutineExecutorFactory(coroutineDispatcher) {
            onIntent<LoginStore.Intent.PhoneInput> {
                if (!state().loginInput.isLoading && state().loginInput.step != LoginInput.Step.PHONE_INPUT)
                    return@onIntent
                val formatted = formatPhoneUseCase(it.value)
                dispatch(Message.PhoneField(validatePhoneUseCase(formatted)))
            }
            onIntent<LoginStore.Intent.CodeInput> {
                if (!state().loginInput.isLoading && state().loginInput.step != LoginInput.Step.CODE_INPUT)
                    return@onIntent
                val formatted = formatCodeUseCase(it.value)
                dispatch(Message.CodeField(validateCodeUseCase(formatted)))
            }
            onIntent<LoginStore.Intent.NewCode> {
                dispatch(Message.Loading)
                launch {
                    sendCodeUseCase(state().loginInput.phone.value)
                    withContext(Dispatchers.Main) {
                        dispatch(Message.ChangeStep(LoginInput.Step.CODE_INPUT))
                    }
                }
            }
            onIntent<LoginStore.Intent.Continue> {
                if (!state().loginInput.isContinueAvailable)
                    return@onIntent
                dispatch(Message.Loading)
                when (state().loginInput.step) {
                    LoginInput.Step.PHONE_INPUT -> {
                        launch {
                            sendCodeUseCase(state().loginInput.phone.value)
                            withContext(Dispatchers.Main) {
                                dispatch(Message.ChangeStep(LoginInput.Step.CODE_INPUT))
                            }
                        }
                    }

                    LoginInput.Step.CODE_INPUT -> {
                        launch {
                            try {
                                signInUseCase(
                                    state().loginInput.phone.value,
                                    state().loginInput.code.value
                                )
                                withContext(Dispatchers.Main) {
                                    publish(LoginStore.Label.AuthorizationCompleted)
                                }
                            } catch (e: Exception) {
                                withContext(Dispatchers.Main) {
                                    dispatch(Message.IllegalCode)
                                }
                            }
                        }
                    }
                }
            }
        },
    ) {

    sealed interface Message {
        data class PhoneField(
            val field: LoginInput.PhoneField,
        ) : Message

        data class CodeField(
            val field: LoginInput.CodeField,
        ) : Message

        data class ChangeStep(
            val step: LoginInput.Step,
        ) : Message

        data object IllegalCode : Message

        data object Loading : Message
    }
}