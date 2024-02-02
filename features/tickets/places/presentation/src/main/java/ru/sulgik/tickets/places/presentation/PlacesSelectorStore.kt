package ru.sulgik.tickets.places.presentation

import com.arkivanov.mvikotlin.core.store.Store

interface PlacesSelectorStore :
    Store<PlacesSelectorStore.Intent, PlacesSelectorStore.State, PlacesSelectorStore.Label> {

    sealed interface Intent {
        data class Select(
            val row: Int,
            val column: Int,
            val cost: Int,
        ) : Intent

        data class Unselect(
            val row: Int,
            val column: Int,
        ) : Intent

    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val totalCost: Int = 0,
        val selectedPlaces: List<SelectedPlace> = emptyList(),
    ) {
        data class SelectedPlace(
            val row: Int,
            val column: Int,
            val price: Int,
        )
    }

    sealed interface Label

}