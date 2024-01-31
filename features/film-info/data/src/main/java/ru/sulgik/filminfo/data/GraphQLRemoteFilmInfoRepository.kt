package ru.sulgik.filminfo.data

import com.apollographql.apollo3.ApolloClient
import filmlist.FindFilmByIdQuery

class GraphQLRemoteFilmInfoRepository(
    private val apolloClient: ApolloClient,
) : RemoteFilmInfoRepository {

    override suspend fun getFilmById(id: String): RemoteFilm {
        val response = apolloClient.query(FindFilmByIdQuery(id)).execute()
        return response.data?.getFilm?.film?.convert()
            ?: throw IllegalStateException(response.exception)
    }

}