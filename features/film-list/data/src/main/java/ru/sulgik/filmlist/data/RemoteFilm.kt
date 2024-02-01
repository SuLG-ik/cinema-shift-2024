package ru.sulgik.filmlist.data

data class RemoteFilm(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val userRating: UserRating,
    val genres: List<String>,
    val countryName: String?,
    val imageUrl: String,
) {
    data class UserRating(
        val imdb: Float,
        val kinopoisk: Float,
    )
}