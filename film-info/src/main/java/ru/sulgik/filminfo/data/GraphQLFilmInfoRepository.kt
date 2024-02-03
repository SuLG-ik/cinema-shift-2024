package ru.sulgik.filminfo.data

import com.apollographql.apollo3.ApolloClient
import filmlist.FindFilmByIdQuery
import ru.sulgik.filminfo.domain.entity.Film
import ru.sulgik.filminfo.domain.repository.FilmInfoRepository

class GraphQLFilmInfoRepository(
    private val apolloClient: ApolloClient,
    private val graphQLConverter: GraphQLConverter,
) : FilmInfoRepository {

    override suspend fun getFilmById(id: String): Film {
        val response = apolloClient.query(FindFilmByIdQuery(id)).execute()
        return response.data?.getFilm?.film?.let(graphQLConverter::convert)
            ?: throw IllegalStateException(response.exception)
    }

}