package ru.sulgik.tickets.userinfo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.userinfo.component.UserInfoInput

@Composable
fun UserInfoInputUI(
    component: UserInfoInput,
    modifier: Modifier = Modifier,
) {
    val userInfo by component.userInfo.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    UserInfoInputScreen(
        info = userInfo,
        isContinueAvailable = isContinueAvailable,
        onFirstNameInput = component::onFirstNameInput,
        onLastNameInput = component::onLastNameInput,
        onMiddleNameInput = component::onMiddleNameInput,
        onPhoneInput = component::onPhoneInput,
        onContinue = component::onContinue,
        onBack = component::onBack,
        modifier = modifier,
    )
}