package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.profile.edit.domain.entity.UserProfileInput

class ValidateEmailUseCase(
    private val userInputValidator: UserInputValidator,
) {

    operator fun invoke(value: String): UserProfileInput.EditableField {
        val result = userInputValidator.validateEmail(value)
        return UserProfileInput.EditableField(
            value = value,
            error = result.error,
        )
    }

}