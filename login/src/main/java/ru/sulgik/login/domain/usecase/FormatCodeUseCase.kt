package ru.sulgik.login.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatCodeUseCase(
    private val formatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return formatter.formatCode(value)
    }

}