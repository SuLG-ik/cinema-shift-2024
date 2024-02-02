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
        isContinueAvailable = state.isContinueAvailable,
        onFirstNameInput = component::onFirstNameInput,
        onLastNameInput = component::onLastNameInput,
        onMiddleNameInput = component::onMiddleNameInput,
        onPhoneInput = component::onPhoneInput,
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
        error = error?.convert(),
    )
}

private fun UserInfoInput.State.UserInfo.Error.convert(): UserInfo.Error {
    return when (this) {
        UserInfoInput.State.UserInfo.Error.DifferentLanguages -> UserInfo.Error.DifferentLanguages
        UserInfoInput.State.UserInfo.Error.IncorrectInput -> UserInfo.Error.IncorrectInput
        UserInfoInput.State.UserInfo.Error.IncorrectLength -> UserInfo.Error.IncorrectLength
    }
}

private fun UserInfoInput.State.UserInfo.LastNameField.toUI(): UserInfo.LastNameField {
    return UserInfo.LastNameField(
        value = value,
        error = error?.convert()
    )
}

private fun UserInfoInput.State.UserInfo.MiddleNameField.toUI(): UserInfo.MiddleNameField {
    return UserInfo.MiddleNameField(
        value = value,
        error = error?.convert()
    )
}

private fun UserInfoInput.State.UserInfo.PhoneField.toUI(): UserInfo.PhoneField {
    return UserInfo.PhoneField(
        value = value,
        isEditable = isEditable,
        error = error?.convert()
    )
}
