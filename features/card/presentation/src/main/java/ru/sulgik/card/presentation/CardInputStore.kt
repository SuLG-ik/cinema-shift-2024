package ru.sulgik.card.presentation

import com.arkivanov.mvikotlin.core.store.Store


interface CardInputStore :
    Store<CardInputStore.Intent, CardInputStore.State, CardInputStore.Label> {

    sealed interface Intent {
        data class NumberInput(val value: String) : Intent
        data class DateInput(val value: String) : Intent
        data class CCVInput(val value: String) : Intent
    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val card: Card = Card(),
    ) {
        data class Card(
            val number: CardNumberField = CardNumberField(),
            val date: CardDateField = CardDateField(),
            val ccv: CardCCVField = CardCCVField(),
        ) {

            data class CardNumberField(
                val value: String = "",
                val error: Error? = Error.IncorrectLength,
            )

            data class CardDateField(
                val value: String = "",
                val error: Error? = Error.IncorrectLength,
            )

            data class CardCCVField(
                val value: String = "",
                val error: Error? = Error.IncorrectLength,
            )


            sealed interface Error {
                data object IncorrectLength : Error
                data object IncorrectValue : Error
            }
        }
    }

    sealed interface Label

}