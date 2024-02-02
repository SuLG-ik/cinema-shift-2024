package ru.sulgik.login.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.login.domain.entity.LoginInput
import ru.sulgik.login.presentation.LoginStore

class LoginComponent(
    componentContext: DIComponentContext,
) : AppComponentContext(componentContext), Login {

    private val store: LoginStore = getStore()

    override val loginInput: Value<LoginInput> = store.values(this).map { it.loginInput }

    override fun onContinue() {
        store.accept(LoginStore.Intent.Continue)
    }

    override fun onPhoneInput(value: String) {
        store.accept(LoginStore.Intent.PhoneInput(value))
    }

    override fun onCodeInput(value: String) {
        store.accept(LoginStore.Intent.CodeInput(value))
    }

}