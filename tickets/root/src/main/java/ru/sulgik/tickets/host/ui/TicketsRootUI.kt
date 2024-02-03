package ru.sulgik.tickets.host.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sulgik.card.component.CardInputUI
import ru.sulgik.tickets.confirmation.ui.ConfirmationUI
import ru.sulgik.tickets.host.component.TicketsRoot
import ru.sulgik.tickets.places.ui.PlacesSelectorUI
import ru.sulgik.tickets.schedule.ui.SeanceSelectorUI
import ru.sulgik.tickets.userinfo.ui.UserInfoInputUI

@Composable
fun TicketsRootUI(
    component: TicketsRoot,
    modifier: Modifier = Modifier,
) {
    Children(stack = component.childStack) {
        TicketsRootNavHost(it.instance, modifier)
    }
}

@Composable
fun TicketsRootNavHost(
    child: TicketsRoot.Child,
    modifier: Modifier,
) {
    when (child) {
        is TicketsRoot.Child.SeanceSelector -> SeanceSelectorUI(
            component = child.component,
            modifier = modifier
        )

        is TicketsRoot.Child.PlacesSelector -> PlacesSelectorUI(
            component = child.component,
            modifier = modifier
        )

        is TicketsRoot.Child.OrderConfirmation -> ConfirmationUI(
            component = child.component,
            modifier = modifier
        )

        is TicketsRoot.Child.CardInput -> CardInputUI(
            component = child.component,
            modifier = modifier,
        )

        is TicketsRoot.Child.UserInput -> UserInfoInputUI(
            component = child.component,
            modifier = modifier,
        )
    }
}