package ru.sulgik.login.domain.usecase

import ru.sulgik.core.validation.user.UserInputFormatter

class FormatPhoneUseCase(
    private val formatter: UserInputFormatter,
) {

    operator fun invoke(value: String): String {
        return formatter.formatPhone(value)
    }

}