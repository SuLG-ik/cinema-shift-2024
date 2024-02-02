package ru.sulgik.login.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.login.domain.entity.LoginInput

interface LoginStore : Store<LoginStore.Intent, LoginStore.State, LoginStore.Label> {

    sealed interface Intent {
        data class PhoneInput(val value: String) : Intent
        data class CodeInput(val value: String) : Intent
        data object Continue : Intent
    }

    data class State(
        val loginInput: LoginInput = LoginInput(
            isContinueAvailable = false,
            isLoading = false,
            phone = LoginInput.PhoneField("", LoginInput.Error.IncorrectLength),
            code = LoginInput.CodeField("", LoginInput.Error.IncorrectLength),
            step = LoginInput.Step.PHONE_INPUT,
        ),
    )

    sealed interface Label

}