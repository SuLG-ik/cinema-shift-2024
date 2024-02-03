package ru.sulgik.profile.edit.domain.usecase

import ru.sulgik.profile.edit.domain.entity.UserProfile
import ru.sulgik.profile.edit.domain.repository.UserProfileRepository

class EditProfileInfoUseCase(
    private val userProfileRepository: UserProfileRepository
) {

    suspend operator fun invoke(profile: UserProfile) {
        return userProfileRepository.editUserProfile(profile)
    }

}