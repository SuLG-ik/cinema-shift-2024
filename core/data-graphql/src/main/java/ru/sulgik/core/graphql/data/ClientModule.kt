package ru.sulgik.core.graphql.data

import com.apollographql.apollo3.ApolloClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private fun createGraphQLClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildConfig.REMOTE_URL)
        .build()
}

val graphQlModule = module {
    singleOf(::createGraphQLClient)
}