package ru.sulgik.ticket.userinfo.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.tickets.userinfo.presentation.UserInfoInputStore

class UserInfoInputComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onContinue: (UserInfoInput.UserData) -> Unit,
) : AppComponentContext(componentContext), UserInfoInput {

    private val store: UserInfoInputStore = getStore()

    override val state: Value<UserInfoInput.State> = store.values(this).map(this::convert)

    private fun convert(state: UserInfoInputStore.State): UserInfoInput.State {
        return UserInfoInput.State(
            isContinueAvailable = state.isContinueAvailable,
            userInfo = state.userInfo.convert()
        )
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        if (store.state.isContinueAvailable) {
            val user = store.state.userInfo
            onContinue.invoke(
                UserInfoInput.UserData(
                    firstName = user.firstName.value,
                    lastName = user.lastName.value,
                    middleName = user.middleName.value,
                    phone = user.phone.value,
                )
            )
        }
    }

    override fun onFirstNameInput(value: String) {
        store.accept(UserInfoInputStore.Intent.FirstNameInput(value))
    }

    override fun onLastNameInput(value: String) {
        store.accept(UserInfoInputStore.Intent.LastNameInput(value))
    }

    override fun onMiddleNameInput(value: String) {
        store.accept(UserInfoInputStore.Intent.MiddleNameInput(value))
    }

    override fun onPhoneInput(value: String) {
        store.accept(UserInfoInputStore.Intent.PhoneInput(value))
    }

}

private fun UserInfoInputStore.State.UserInfo.convert(): UserInfoInput.State.UserInfo {
    return UserInfoInput.State.UserInfo(
        firstName = firstName.convert(),
        lastName = lastName.convert(),
        middleName = middleName.convert(),
        phone = phone.convert()
    )
}

private fun UserInfoInputStore.State.UserInfo.PhoneField.convert(): UserInfoInput.State.UserInfo.PhoneField {
    return UserInfoInput.State.UserInfo.PhoneField(
        value = value,
        isEditable = isEditable,
        error = error?.convert(),
    )
}

private fun UserInfoInputStore.State.UserInfo.MiddleNameField.convert(): UserInfoInput.State.UserInfo.MiddleNameField {
    return UserInfoInput.State.UserInfo.MiddleNameField(
        value = value,
        error = error?.convert()
    )
}

private fun UserInfoInputStore.State.UserInfo.LastNameField.convert(): UserInfoInput.State.UserInfo.LastNameField {
    return UserInfoInput.State.UserInfo.LastNameField(
        value = value,
        error = error?.convert()
    )
}

private fun UserInfoInputStore.State.UserInfo.Error.convert(): UserInfoInput.State.UserInfo.Error {
    return when (this) {
        UserInfoInputStore.State.UserInfo.Error.DifferentLanguages -> UserInfoInput.State.UserInfo.Error.DifferentLanguages
        UserInfoInputStore.State.UserInfo.Error.IncorrectInput -> UserInfoInput.State.UserInfo.Error.IncorrectInput
        UserInfoInputStore.State.UserInfo.Error.IncorrectLength -> UserInfoInput.State.UserInfo.Error.IncorrectLength
    }
}

private fun UserInfoInputStore.State.UserInfo.FirstNameField.convert(): UserInfoInput.State.UserInfo.FirstNameField {
    return UserInfoInput.State.UserInfo.FirstNameField(
        value = value,
        error = error?.convert(),
    )
}


