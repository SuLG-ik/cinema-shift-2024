package ru.sulgik.login.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.login.domain.entity.LoginInput
import ru.sulgik.login.presentation.LoginStore

class LoginComponent(
    componentContext: DIComponentContext,
    onAuthorized: () -> Unit,
) : AppComponentContext(componentContext), Login {

    private val coroutineScope = coroutineScope()
    private val store: LoginStore = getStore()

    init {
        store.labels.onEach {
            when (it) {
                LoginStore.Label.AuthorizationCompleted -> onAuthorized()
            }
        }.launchIn(coroutineScope)
    }

    override val loginInput: Value<LoginInput> = store.values(this).map { it.loginInput }

    override fun onContinue() {
        store.accept(LoginStore.Intent.Continue)
    }

    override fun onNewCode() {
        store.accept(LoginStore.Intent.NewCode)
    }

    override fun onPhoneInput(value: String) {
        store.accept(LoginStore.Intent.PhoneInput(value))
    }

    override fun onCodeInput(value: String) {
        store.accept(LoginStore.Intent.CodeInput(value))
    }

}