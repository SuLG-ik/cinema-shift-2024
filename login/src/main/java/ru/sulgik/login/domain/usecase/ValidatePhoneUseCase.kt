package ru.sulgik.login.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.login.domain.entity.LoginInput

class ValidatePhoneUseCase(
    private val validator: UserInputValidator,
) {

    operator fun invoke(value: String): LoginInput.PhoneField {
        val result = validator.validatePhone(value)
        return LoginInput.PhoneField(
            value = result.value,
            error = result.error,
        )
    }

}