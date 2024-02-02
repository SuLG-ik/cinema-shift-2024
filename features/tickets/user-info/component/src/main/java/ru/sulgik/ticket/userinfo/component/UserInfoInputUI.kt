package ru.sulgik.ticket.userinfo.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.userinfo.ui.UserInfo
import ru.sulgik.tickets.userinfo.ui.UserInfoInputScreen

@Composable
fun UserInfoInputUI(
    component: UserInfoInput,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()
    UserInfoInputScreen(
        info = state.userInfo.toUI(),
        onFirstNameInput = component::onFirstNameInput,
        onLastNameInput = component::onLastNameInput,
        onMiddleNameInput = component::onMiddleNameInput,
        onPhoneInput = component::onPhoneInput,
        isContinueAvailable = state.isContinueAvailable,
        onContinue = component::onContinue,
        onBack = component::onBack,
        modifier = modifier,
    )
}

private fun UserInfoInput.State.UserInfo.toUI(): UserInfo {
    return UserInfo(
        name = firstName.toUI(),
        lastName = lastName.toUI(),
        middleName = middleName.toUI(),
        phone = phone.toUI()
    )
}

private fun UserInfoInput.State.UserInfo.FirstNameField.toUI(): UserInfo.NameField {
    return UserInfo.NameField(
        value = value,
    )
}

private fun UserInfoInput.State.UserInfo.LastNameField.toUI(): UserInfo.LastNameField {
    return UserInfo.LastNameField(
        value = value,
    )
}

private fun UserInfoInput.State.UserInfo.MiddleNameField.toUI(): UserInfo.MiddleNameField {
    return UserInfo.MiddleNameField(
        value = value
    )
}

private fun UserInfoInput.State.UserInfo.PhoneField.toUI(): UserInfo.PhoneField {
    return UserInfo.PhoneField(
        value = value,
        isEditable = isEditable
    )
}
