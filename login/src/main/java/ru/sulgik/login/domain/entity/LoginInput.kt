package ru.sulgik.login.domain.entity

import ru.sulgik.core.validation.user.UserInputError

data class LoginInput(
    val isContinueAvailable: Boolean,
    val isLoading: Boolean,
    val step: Step,
    val phone: PhoneField,
    val code: CodeField,
) {

    enum class Step {
        PHONE_INPUT, CODE_INPUT
    }

    data class PhoneField(
        val value: String,
        val error: UserInputError?,
    )

    data class CodeField(
        val value: String,
        val error: UserInputError?,
    )

}