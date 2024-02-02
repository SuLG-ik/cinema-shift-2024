package ru.sulgik.tickets.userinfo.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatMiddleNameUseCase(
    private val userInputFormatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return userInputFormatter.formatMiddleName(value)
    }

}