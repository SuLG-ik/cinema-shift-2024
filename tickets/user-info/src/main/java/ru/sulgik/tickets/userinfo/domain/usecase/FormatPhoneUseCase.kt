package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatPhoneUseCase(
    private val userInputFormatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return userInputFormatter.formatPhone(value)
    }

}