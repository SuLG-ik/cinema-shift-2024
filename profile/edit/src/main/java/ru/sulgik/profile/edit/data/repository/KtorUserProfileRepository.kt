package ru.sulgik.profile.edit.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import ru.sulgik.profile.edit.data.converter.KtorUserConverter
import ru.sulgik.profile.edit.data.model.EditUserRequest
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
        client.patch("/users/profile") {
            contentType(Json)
            setBody(EditUserRequest(
                phone = profile.phone,
                profile = EditUserRequest.Profile(
                    firstName = profile.firstName,
                    lastName = profile.lastName,
                    middleName = profile.middleName,
                    email = profile.email,
                    city = profile.city,
                    phone = profile.phone

                )
            ))
        }
    }

}