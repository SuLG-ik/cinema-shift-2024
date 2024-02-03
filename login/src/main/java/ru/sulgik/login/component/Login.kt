package ru.sulgik.login.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.login.domain.entity.LoginInput

interface Login {

    val loginInput: Value<LoginInput>

    fun onPhoneInput(value: String)

    fun onCodeInput(value: String)

    fun onContinue()

    fun onNewCode()
}