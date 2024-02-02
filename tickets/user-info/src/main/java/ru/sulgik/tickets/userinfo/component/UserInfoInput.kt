package ru.sulgik.tickets.userinfo.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo


interface UserInfoInput {

    val userInfo: Value<UserInfo>

    val isContinueAvailable: Value<Boolean>

    fun onBack()

    fun onContinue()

    fun onFirstNameInput(value: String)

    fun onLastNameInput(value: String)

    fun onMiddleNameInput(value: String)

    fun onPhoneInput(value: String)

}