package ru.sulgik.card.component

import com.arkivanov.decompose.value.Value


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
            )

            data class CardDateField(
                val value: String,
            )

            data class CardCCVField(
                val value: String,
            )
        }

    }
}