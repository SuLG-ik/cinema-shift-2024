package ru.sulgik.tickets.data

import com.apollographql.apollo3.ApolloClient
import ru.sulgik.tickets.domain.entity.Film
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.repository.FilmInfoRepository
import tickets.GetFilmScheduleQuery
import tickets.GetFilmTitleQuery

internal class GraphQLFilmInfoRepository(
    private val apolloClient: ApolloClient,
    private val converter: GraphQLConverter,
) : FilmInfoRepository {

    override suspend fun getFilmScheduleById(id: String): List<Schedule> {
        val response = apolloClient.query(GetFilmScheduleQuery(id)).execute()
        val data = response.data ?: throw IllegalStateException(response.exception)
        return converter.convert(data.getFilmSchedule.schedules)
    }

    override suspend fun getShortFilm(id: String): Film {
        val response = apolloClient.query(GetFilmTitleQuery(id)).execute()
        val data = response.data ?: throw IllegalStateException(response.exception)
        return converter.convert(data.getFilm.film)
    }

}