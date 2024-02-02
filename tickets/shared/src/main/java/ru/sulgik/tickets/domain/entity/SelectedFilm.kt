package ru.sulgik.tickets.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelectedFilm(
    val filmId: String,
    val title: String,
)

