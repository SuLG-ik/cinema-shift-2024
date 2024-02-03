package ru.sulgik.login.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType
import ru.sulgik.login.data.model.SendOTPRequest
import ru.sulgik.login.data.model.SendOTPResponse
import ru.sulgik.login.data.model.SignInRequest
import ru.sulgik.login.data.model.SignInResponse
import ru.sulgik.login.domain.entity.SentOTP
import ru.sulgik.login.domain.entity.SignInCompleted
import ru.sulgik.login.domain.repository.RemoteLoginRepository

class KtorRemoteLoginRepository(
    private val client: HttpClient,
) : RemoteLoginRepository {
    override suspend fun sendOTP(phone: String): SentOTP {
        val response = client.post("/auth/otp") {
            contentType(Json)
            setBody(SendOTPRequest("7$phone"))
        }.body<SendOTPResponse>()
        return SentOTP(response.retryDelay)
    }

    override suspend fun signIn(phone: String, otp: String): SignInCompleted {
        val response = client.post("/users/signin") {
            contentType(Json)
            setBody(SignInRequest("7$phone", otp))
        }.body<SignInResponse>()
        return SignInCompleted(response.token)
    }


}