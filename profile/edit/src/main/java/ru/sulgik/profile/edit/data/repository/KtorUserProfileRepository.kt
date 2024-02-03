package ru.sulgik.profile.edit.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.sulgik.profile.edit.data.converter.KtorUserConverter
import ru.sulgik.profile.edit.data.model.UserDetailsResponse
import ru.sulgik.profile.edit.domain.entity.UserProfile
import ru.sulgik.profile.edit.domain.repository.UserProfileRepository

class KtorUserProfileRepository(
    private val client: HttpClient,
    private val converter: KtorUserConverter,
) : UserProfileRepository {

    override suspend fun loadUserProfile(): UserProfile {
        val response = client.get("/users/session").body<UserDetailsResponse>()
        return converter.convert(response)
    }

    override suspend fun editUserProfile(profile: UserProfile) {
        TODO("Not yet implemented")
    }

}