package ru.sulgik.login.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.login.domain.entity.LoginInput

class ValidateCodeUseCase(
    private val validator: UserInputValidator,
) {

    operator fun invoke(value: String): LoginInput.CodeField {
        val result = validator.validateCode(value)
        return LoginInput.CodeField(
            value = result.value,
            error = result.error
        )
    }

}