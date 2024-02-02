package ru.sulgik.tickets.confirmation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.tickets.confirmation.component.Confirmation

@Composable
fun ConfirmationUI(
    component: Confirmation,
    modifier: Modifier = Modifier,
) {
    ConfirmationScreen(
        confirmation = component.state,
        onContinue = component::onContinue,
        onBack = component::onBack,
        modifier = modifier,
    )
}