package ru.sulgik.login.domain.usecase

import ru.sulgik.login.domain.entity.LoginInput
import ru.sulgik.login.domain.entity.LoginInput.Step.*

class IsContinueAvailableUseCase {

    operator fun invoke(loginInput: LoginInput): Boolean {
        return !loginInput.isLoading && when (loginInput.step) {
            PHONE_INPUT -> loginInput.phone.error == null
            CODE_INPUT -> loginInput.code.error == null
        }
    }

}