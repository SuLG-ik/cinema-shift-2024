package ru.sulgik.tickets.userinfo.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.tickets.domain.entity.SelectedUser
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo
import ru.sulgik.tickets.userinfo.presentation.UserInfoInputStore

class UserInfoInputComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onContinue: (SelectedUser) -> Unit,
) : AppComponentContext(componentContext), UserInfoInput {

    private val store: UserInfoInputStore = getStore()

    private val state: Value<UserInfoInputStore.State> = store.values(this)

    override val userInfo: Value<UserInfo> = state.map { it.userInfo }

    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        if (store.state.isContinueAvailable) {
            val user = store.state.userInfo
            onContinue.invoke(
                SelectedUser(
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
