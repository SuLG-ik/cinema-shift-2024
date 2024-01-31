package ru.sulgik.filmlist.data

import com.apollographql.apollo3.ApolloClient
import filmlist.LoadFilmListQuery
import java.lang.IllegalStateException

class GraphQLRemoteFilmListRepository(
    private val apolloClient: ApolloClient,
) : RemoteFilmListRepository {

    override suspend fun getAllFilms(): List<RemoteFilm> {
        val response = apolloClient.query(LoadFilmListQuery()).execute()
        return response.data?.convert() ?: throw IllegalStateException(response.exception)
    }

}