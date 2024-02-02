package ru.sulgik.card.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.card.ui.Card


interface CardInput {

    val state: Value<State>

    fun onBack()

    fun onContinue()

    fun onCardNumberInput(value: String)

    fun onCardDateInput(value: String)

    fun onCardCCVInput(value: String)

    data class CardData(
        val number: String,
        val date: String,
        val ccv: String,
    )

    data class State(
        val isContinueAvailable: Boolean,
        val card: Card,
    ) {


        data class Card(
            val cardNumber: CardNumberField,
            val date: CardDateField,
            val ccv: CardCCVField,
        ) {

            data class CardNumberField(
                val value: String,
                val error: Error?,
            )

            data class CardDateField(
                val value: String,
                val error: Error?,
            )

            data class CardCCVField(
                val value: String,
                val error: Error?,
            )


            sealed interface Error {
                data object IncorrectLength : Error
                data object IncorrectValue : Error
            }
        }

    }
}