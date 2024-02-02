package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo

class ValidateLastNameUseCase(
    private val userInputValidator: UserInputValidator,
) {

    operator fun invoke(value: String): UserInfo.LastNameField {
        val result = userInputValidator.validateLastName(value)
        return UserInfo.LastNameField(
            value = value,
            error = result.error,
        )
    }

}