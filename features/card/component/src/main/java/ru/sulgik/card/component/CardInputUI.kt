package ru.sulgik.card.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.card.ui.Card
import ru.sulgik.card.ui.CardInputScreen

@Composable
fun CardInputUI(
    component: CardInput,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()
    CardInputScreen(
        card = state.card.toUI(),
        isContinueAvailable = state.isContinueAvailable,
        onContinue = component::onContinue,
        onBack = component::onBack,
        onCardNumberInput = component::onCardNumberInput,
        onCardDateInput = component::onCardDateInput,
        onCardCCVInput = component::onCardCCVInput,
        modifier = modifier,
    )
}

private fun CardInput.State.Card.toUI(): Card {
    return Card(
        cardNumber = cardNumber.toUI(),
        date = date.toUI(),
        ccv = ccv.toUI(),
    )
}

private fun CardInput.State.Card.CardCCVField.toUI(): Card.CardCCVField {
    return Card.CardCCVField(value, error?.toUI())
}

private fun CardInput.State.Card.CardDateField.toUI(): Card.CardDateField {
    return Card.CardDateField(value, error?.toUI())
}

private fun CardInput.State.Card.Error.toUI(): Card.Error {
    return when (this) {
        CardInput.State.Card.Error.IncorrectLength -> Card.Error.IncorrectLength
        CardInput.State.Card.Error.IncorrectValue -> Card.Error.IncorrectValue
    }
}

private fun CardInput.State.Card.CardNumberField.toUI(): Card.CardNumberField {
    return Card.CardNumberField(value, error?.toUI())
}
