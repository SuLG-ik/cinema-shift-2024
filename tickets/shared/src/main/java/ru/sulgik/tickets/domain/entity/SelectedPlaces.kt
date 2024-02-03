package ru.sulgik.tickets.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class SelectedPlaces(
    val totalCost: Int,
    val places: List<Place>,
) {

    @Serializable
    data class Place(
        val row: Int,
        val column: Int,
        val price: Int,
    )

}