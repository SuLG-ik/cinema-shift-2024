package ru.sulgik.host.ui.films

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sulgik.filminfo.ui.FilmInfoUI
import ru.sulgik.filmlist.component.FilmListUI
import ru.sulgik.host.component.films.Films
import ru.sulgik.tickets.host.ui.TicketsRootUI

@Composable
fun FilmsUI(
    component: Films,
    modifier: Modifier,
) {
    Children(stack = component.childState) {
        FilmsNavHost(child = it.instance, modifier = modifier)
    }
}

@Composable
fun FilmsNavHost(
    child: Films.Child, modifier: Modifier
) {
    when (child) {
        is Films.Child.FilmInfo -> FilmInfoUI(component = child.component, modifier = modifier)
        is Films.Child.FilmList -> FilmListUI(component = child.component, modifier = modifier)
        is Films.Child.TicketOrder -> TicketsRootUI(
            component = child.component, modifier = modifier
        )
    }
}