package ru.sulgik.core.auth.repository

import ru.sulgik.core.auth.domain.AuthScope

interface LocalAuthRepository {

    suspend fun saveUser(phone: String, token: String)

    suspend fun getScope(): AuthScope?

}