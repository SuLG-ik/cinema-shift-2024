package ru.sulgik.tickets.data

import com.apollographql.apollo3.ApolloClient
import filmlist.GetFilmScheduleQuery
import filmlist.GetFilmTitleQuery

class GraphQLRemoteFilmInfoRepository(
    private val apolloClient: ApolloClient,
    private val converter: GraphQLConverter,
) : RemoteFilmInfoRepository {

    override suspend fun getFilmScheduleById(id: String): List<RemoteSchedule> {
        val response = apolloClient.query(GetFilmScheduleQuery(id)).execute()
        val data = response.data ?: throw IllegalStateException(response.exception)
        return converter.convert(data.getFilmSchedule.schedules)
    }

    override suspend fun getShortFilm(id: String): RemoteFilm {
        val response = apolloClient.query(GetFilmTitleQuery(id)).execute()
        val data = response.data ?: throw IllegalStateException(response.exception)
        return converter.convert(data.getFilm.film)
    }

}