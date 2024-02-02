package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo

class ValidateFirstNameUseCase(
    private val userInputValidator: UserInputValidator,
) {

    operator fun invoke(value: String): UserInfo.FirstNameField {
        val result = userInputValidator.validateFirstName(value)
        return UserInfo.FirstNameField(
            value = value,
            error = result.error,
        )
    }

}