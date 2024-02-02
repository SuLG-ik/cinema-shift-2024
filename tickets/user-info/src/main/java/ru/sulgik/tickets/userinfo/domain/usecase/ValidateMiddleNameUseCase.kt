package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo

class ValidateMiddleNameUseCase(
    private val userInputValidator: UserInputValidator,
) {

    operator fun invoke(value: String): UserInfo.MiddleNameField {
        val result = userInputValidator.validateMiddleName(value)
        return UserInfo.MiddleNameField(
            value = value,
            error = result.error,
        )
    }

}