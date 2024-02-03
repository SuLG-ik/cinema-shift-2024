package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputValidator
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo

class ValidatePhoneUseCase(
    private val userInputValidator: UserInputValidator,
) {

    operator fun invoke(value: String, isEditable: Boolean): UserInfo.PhoneField {
        val result = userInputValidator.validatePhone(value)
        return UserInfo.PhoneField(
            value = value,
            isEditable = isEditable,
            error = result.error,
        )
    }

}