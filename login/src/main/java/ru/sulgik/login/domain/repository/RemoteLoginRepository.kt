package ru.sulgik.login.domain.repository

import ru.sulgik.login.domain.entity.SentOTP
import ru.sulgik.login.domain.entity.SignInCompleted

interface RemoteLoginRepository {

    suspend fun sendOTP(phone: String): SentOTP

    suspend fun signIn(phone: String, otp: String): SignInCompleted

}