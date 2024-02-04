package ru.sulgik.profile.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelectedFilm(
    val filmId: String,
    val title: String,
)

