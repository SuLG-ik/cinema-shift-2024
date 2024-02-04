package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatFirstNameUseCase(
    private val userInputFormatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return userInputFormatter.formatFirstName(value)
    }

}