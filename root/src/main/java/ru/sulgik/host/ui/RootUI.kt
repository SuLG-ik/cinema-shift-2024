package ru.sulgik.host.ui

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.host.component.Root
import ru.sulgik.host.ui.films.FilmsUI
import ru.sulgik.root.R
import ru.sulgik.uikit.iconpack.UIKitIconPack
import ru.sulgik.uikit.iconpack.uikiticonpack.Movie
import ru.sulgik.uikit.iconpack.uikiticonpack.Ticket
import ru.sulgik.uikit.iconpack.uikiticonpack.User

@Composable
fun RootUI(
    component: Root,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavItem.values().forEach {

                    NavigationBarItem(
                        selected = childStack.active.configuration,
                        onClick = { /*TODO*/ },
                        icon = { /*TODO*/ })
                }
            }
        },
        modifier = modifier,
    )
    Children(stack = component.childStack) {
        RootNavHost(it.instance, modifier)
    }
}

enum class NavItem(
    val title: Int,
    val image: ImageVector,
    val config: Root.Config,
) {
    MOVIES(
        ru.sulgik.root.R.string.nav_item_afisha,
        UIKitIconPack.Movie,
        Root.Config.FilmList,
    ),
    Tickets(
        ru.sulgik.root.R.string.nav_item_tickets,
        UIKitIconPack.Ticket,
        Root.Config.Tickets,
    ),
    Profile(
        ru.sulgik.root.R.string.nav_item_profile,
        UIKitIconPack.User,
        Root.Config.Tickets,
    )
}

@Composable
fun RootNavHost(
    child: Root.Child,
    modifier: Modifier,
) {
    when (child) {
        is Root.Child.Films -> FilmsUI(child.component, modifier)
        Root.Child.Profile -> TODO()
        Root.Child.Tickets -> TODO()
    }
}