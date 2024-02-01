package ru.sulgik.tickets.host.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sulgik.tickets.places.component.PlacesSelectorUI
import ru.sulgik.tickets.schedule.component.SeanceSelectorUI

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
        is TicketsRoot.Child.SeanceSelector -> SeanceSelectorUI(child.component, modifier)
        is TicketsRoot.Child.PlacesSelector -> PlacesSelectorUI(child.component, modifier)
    }
}