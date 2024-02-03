package ru.sulgik.core.ktor.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.AuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sulgik.core.auth.repository.LocalAuthRepository
import ru.sulgik.core.ktor.data.BuildConfig.REMOTE_URL

private fun createKtorClient(localAuthRepository: LocalAuthRepository): HttpClient {
    return HttpClient(OkHttp) {
        defaultRequest {
            url(REMOTE_URL)
        }
        install(Logging) {
            this.level = LogLevel.ALL
            logger = AndroidLogger("KtorClient")
        }
        install(ContentNegotiation) {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }
        Auth {
            this.providers += object : AuthProvider {
                @Deprecated("")
                override val sendWithoutRequest: Boolean = false

                override suspend fun addRequestHeaders(
                    request: HttpRequestBuilder,
                    authHeader: HttpAuthHeader?
                ) {
                    val authScope = localAuthRepository.getScope() ?: return
                    request.headers {
                        set(Authorization, "Bearer " + authScope.token)
                    }
                }

                override fun isApplicable(auth: HttpAuthHeader): Boolean {
                    return true
                }
            }
        }
    }
}

class AndroidLogger(
    private val tag: String
) : Logger {

    override fun log(message: String) {
        Log.d(tag, message)
    }

}

val ktorModule = module {
    singleOf(::createKtorClient)
}