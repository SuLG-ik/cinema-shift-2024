package ru.sulgik.tickets.confirmation.domain.entity

import kotlinx.serialization.Serializable
import ru.sulgik.tickets.domain.entity.SelectedFilm
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.domain.entity.SelectedSeance

@Serializable
data class ConfirmationData(
    val film: SelectedFilm,
    val seance: SelectedSeance,
    val places: SelectedPlaces,
)