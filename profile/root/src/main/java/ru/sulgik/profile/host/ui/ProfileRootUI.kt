package ru.sulgik.profile.host.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.sulgik.login.ui.LoginInputUI
import ru.sulgik.profile.edit.ui.ProfileEditUI
import ru.sulgik.profile.host.component.ProfileRoot

@Composable
fun ProfileRootUI(
    component: ProfileRoot,
    modifier: Modifier = Modifier,
) {
    Children(stack = component.childStack) {
        ProfileRootNavHost(it.instance, modifier)
    }
}

@Composable
fun ProfileRootNavHost(
    child: ProfileRoot.Child,
    modifier: Modifier,
) {
    when (child) {
        is ProfileRoot.Child.Edit -> ProfileEditUI(
            component = child.component,
            modifier = modifier
        )
        ProfileRoot.Child.Loading -> TODO()
        is ProfileRoot.Child.Login -> LoginInputUI(
            component = child.component,
            modifier = modifier,
        )
    }
}