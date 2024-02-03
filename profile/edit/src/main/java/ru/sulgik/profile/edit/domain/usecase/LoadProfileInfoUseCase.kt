package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.profile.edit.domain.entity.UserProfile
import ru.sulgik.profile.edit.domain.repository.UserProfileRepository

class LoadProfileInfoUseCase(
    private val userProfileRepository: UserProfileRepository
) {

    suspend operator fun invoke(): UserProfile {
        return userProfileRepository.loadUserProfile()
    }

}