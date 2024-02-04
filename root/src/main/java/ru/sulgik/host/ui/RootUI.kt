package ru.sulgik.host.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.host.component.Root
import ru.sulgik.host.ui.films.FilmsUI
import ru.sulgik.profile.host.ui.ProfileRootUI
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
                        selected = childStack.active.configuration == it.config,
                        onClick = { component.onNavigate(it.config) },
                        icon = { Icon(it.image, contentDescription = null) },
                        label = { Text(stringResource(id = it.title)) }
                    )
                }
            }
        },
        modifier = modifier,
    ) { paddingValues ->
        Children(childStack, modifier = Modifier.padding(paddingValues)) {
            RootNavHost(it.instance, modifier = Modifier.fillMaxWidth())
        }
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
        Root.Config.Profile,
    )
}

@Composable
fun RootNavHost(
    child: Root.Child,
    modifier: Modifier,
) {
    when (child) {
        is Root.Child.Films -> FilmsUI(child.component, modifier)
        is Root.Child.Profile -> ProfileRootUI(child.component, modifier)
        Root.Child.Tickets -> TODO()
    }
}