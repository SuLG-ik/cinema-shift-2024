package ru.sulgik.login.domain.usecase

import ru.sulgik.core.auth.repository.LocalAuthRepository

class SaveAuthScopeUseCase(
    private val localAuthRepository: LocalAuthRepository,
) {

    suspend operator fun invoke(phone: String, token: String) {
        localAuthRepository.saveUser(phone, token)
    }

}