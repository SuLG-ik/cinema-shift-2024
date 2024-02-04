package ru.sulgik.login.domain.usecase

import ru.sulgik.login.domain.entity.SignInCompleted
import ru.sulgik.login.domain.repository.RemoteLoginRepository

class SignInUseCase(
    private val remoteLoginRepository: RemoteLoginRepository,
) {

    suspend operator fun invoke(phone: String, code: String): SignInCompleted {
        return remoteLoginRepository.signIn(phone, code)
    }

}