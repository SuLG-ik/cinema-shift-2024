package ru.sulgik.tickets.confirmation.component

import com.arkivanov.decompose.value.Value
import java.time.LocalDate
import java.time.LocalTime


interface Confirmation {

    val state: ConfirmationData

    fun onBack()

    fun onContinue()

    data class ConfirmationData(
        val film: FilmInfo,
        val seance: SeanceInfo,
        val place: PlaceInfo,
        val order: OrderInfo,
    ) {

        data class FilmInfo(
            val filmId: String,
            val title: String,
        )

        data class SeanceInfo(
            val date: LocalDate,
            val time: LocalTime,
            val hallType: HallType,
        )

        enum class HallType {
            RED, GREEN, BLUE, UNKNOWN,
        }

        data class PlaceInfo(
            val places: List<Place>,
        )

        data class Place(
            val row: Int,
            val column: Int,
        )

        data class OrderInfo(
            val totalCost: Int,
        )

    }

}