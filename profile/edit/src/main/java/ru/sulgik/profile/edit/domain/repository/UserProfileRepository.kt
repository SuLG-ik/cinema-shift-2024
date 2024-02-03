package ru.sulgik.profile.edit.domain.repository

import ru.sulgik.profile.edit.domain.entity.UserProfile

interface UserProfileRepository {

    suspend fun loadUserProfile(): UserProfile

    suspend fun editUserProfile(profile: UserProfile)

}