package ru.sulgik.filminfo.data

data class RemoteFilm(
    val title: String,
    val subtitle: String,
    val userRating: UserRating,
    val genres: List<String>,
    val countryName: String?,
    val imageUrl: String,
    val description: String,
) {
    data class UserRating(
        val imdb: Float,
        val kinopoisk: Float,
    )
}