package ru.sulgik.core.data

import com.apollographql.apollo3.ApolloClient

fun createGraphQLClient(): ApolloClient {
    return ApolloClient.Builder()
        .serverUrl(BuildConfig.REMOTE_URL)
        .build()
}