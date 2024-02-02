package ru.sulgik.card.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.sulgik.card.presentation.CardInputStore
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values

class CardInputComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onContinue: (CardInput.CardData) -> Unit,
) : AppComponentContext(componentContext), CardInput {

    private val store: CardInputStore = getStore()

    override val state: Value<CardInput.State> = store.values(this).map(this::convert)

    private fun convert(state: CardInputStore.State): CardInput.State {
        return CardInput.State(
            isContinueAvailable = state.isContinueAvailable, card = state.card.convert()

        )
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        if (store.state.isContinueAvailable) {
            val card = store.state.card
            onContinue.invoke(
                CardInput.CardData(
                    number = card.number.value,
                    date = card.date.value,
                    ccv = card.ccv.value,
                )
            )
        }
    }

    override fun onCardNumberInput(value: String) {
        store.accept(CardInputStore.Intent.NumberInput(value))
    }

    override fun onCardDateInput(value: String) {
        store.accept(CardInputStore.Intent.DateInput(value))
    }

    override fun onCardCCVInput(value: String) {
        store.accept(CardInputStore.Intent.CCVInput(value))
    }

}

private fun CardInputStore.State.Card.convert(): CardInput.State.Card {
    return CardInput.State.Card(
        cardNumber = number.convert(),
        date = date.convert(),
        ccv = ccv.convert()
    )
}

private fun CardInputStore.State.Card.CardCCVField.convert(): CardInput.State.Card.CardCCVField {
    return CardInput.State.Card.CardCCVField(
        value = value,
    )
}

private fun CardInputStore.State.Card.CardDateField.convert(): CardInput.State.Card.CardDateField {
    return CardInput.State.Card.CardDateField(
        value = value,
    )
}

private fun CardInputStore.State.Card.CardNumberField.convert(): CardInput.State.Card.CardNumberField {
    return CardInput.State.Card.CardNumberField(
        value = value,
    )
}

