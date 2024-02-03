package ru.sulgik.core.ktor.data

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.sulgik.core.ktor.data.BuildConfig.REMOTE_URL

private fun createKtorClient(): HttpClient {
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