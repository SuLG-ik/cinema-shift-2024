package ru.sulgik.login.domain.usecase

import ru.sulgik.login.domain.entity.SentOTP
import ru.sulgik.login.domain.repository.RemoteLoginRepository

class SendCodeUseCase(
    private val remoteLoginRepository: RemoteLoginRepository,
) {

    suspend operator fun invoke(value: String): SentOTP {
        return remoteLoginRepository.sendOTP(value)
    }

}