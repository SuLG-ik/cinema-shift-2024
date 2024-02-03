package ru.sulgik.login.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.login.component.Login

@Composable
fun LoginInputUI(
    component: Login,
    modifier: Modifier = Modifier
) {
    val input by component.loginInput.subscribeAsState()
    LoginInputScreen(
        input = input,
        onPhoneInput = component::onPhoneInput,
        onCodeInput = component::onCodeInput,
        onContinue = component::onContinue,
        onNewCode = component::onContinue,
        modifier = modifier,
    )
}