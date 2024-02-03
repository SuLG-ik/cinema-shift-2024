package ru.sulgik.login.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.core.validation.user.UserInputError
import ru.sulgik.login.domain.entity.LoginInput

interface LoginStore : Store<LoginStore.Intent, LoginStore.State, LoginStore.Label> {

    sealed interface Intent {
        data class PhoneInput(val value: String) : Intent
        data class CodeInput(val value: String) : Intent
        data object Continue : Intent
        data object NewCode : Intent
    }

    data class State(
        val loginInput: LoginInput = LoginInput(
            isContinueAvailable = false,
            isLoading = false,
            phone = LoginInput.PhoneField("", UserInputError.IncorrectLength),
            code = LoginInput.CodeField("", UserInputError.IncorrectLength),
            step = LoginInput.Step.PHONE_INPUT,
        ),
    )

    sealed interface Label {
        data object AuthorizationCompleted: Label
    }

}