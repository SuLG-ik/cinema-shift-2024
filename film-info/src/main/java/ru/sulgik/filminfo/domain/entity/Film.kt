package ru.sulgik.filminfo.domain.entity

data class Film(
    val id: String,
    val title: String,
    val subtitle: String,
    val userRating: UserRating,
    val genres: List<String>,
    val countryName: String?,
    val imageUrl: String,
    val description: String,
    val ageRating: AgeRating,
    val runtime: Int,
    val directors: List<Director>,
    val actors: List<Actor>,
    val releaseDate: String,
) {

    data class Director(
        val fullName: String,
        val id: String,
        val professions: List<Profession>,
    )


    data class Actor(
        val fullName: String,
        val id: String,
        val professions: List<Profession>,
    )

    enum class Profession {
        ACTOR,
        DIRECTOR,
        UNKNOWN
    }

    data class UserRating(
        val imdb: Float,
        val kinopoisk: Float,
    )

    enum class AgeRating {
        G,
        NC17,
        PG,
        PG13,
        R,
        UNKNOWN
    }

}