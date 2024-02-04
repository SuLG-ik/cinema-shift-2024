package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatLastNameUseCase(
    private val userInputFormatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return userInputFormatter.formatLastName(value)
    }

}