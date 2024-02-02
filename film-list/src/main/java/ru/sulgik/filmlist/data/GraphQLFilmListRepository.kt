package ru.sulgik.filmlist.data

import com.apollographql.apollo3.ApolloClient
import filmlist.LoadFilmListQuery
import ru.sulgik.filmlist.data.converter.RemoteFilmConverter
import ru.sulgik.filmlist.domain.entity.Film
import ru.sulgik.filmlist.domain.repository.FilmListRepository

class GraphQLFilmListRepository(
    private val apolloClient: ApolloClient,
    private val converter: RemoteFilmConverter,
) : FilmListRepository {

    override suspend fun getAllFilms(): List<Film> {
        val response = apolloClient.query(LoadFilmListQuery()).execute()
        return response.data?.getCinemaToday?.films?.map { converter.convert(it) }
            ?: throw IllegalStateException(response.exception)
    }

}