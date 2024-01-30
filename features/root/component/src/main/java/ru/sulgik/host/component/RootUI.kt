package ru.sulgik.host.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sulgik.filmlist.component.FilmListUI

@Composable
fun RootUI(
    component: Root,
    modifier: Modifier = Modifier,
) {
    Children(stack = component.childStack) {
        RootNavHost(it.instance, modifier)
    }
}

@Composable
fun RootNavHost(
    child: Root.Child,
    modifier: Modifier,
) {
    when (child) {
        is Root.Child.FilmList -> FilmListUI(child.component, modifier)
        Root.Child.Profile -> TODO()
        Root.Child.Tickets -> TODO()
    }
}