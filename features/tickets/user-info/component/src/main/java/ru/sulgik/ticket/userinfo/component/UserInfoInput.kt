package ru.sulgik.ticket.userinfo.component

import com.arkivanov.decompose.value.Value


interface UserInfoInput {

    val state: Value<State>

    fun onBack()

    fun onContinue()

    fun onFirstNameInput(value: String)

    fun onLastNameInput(value: String)

    fun onMiddleNameInput(value: String)

    fun onPhoneInput(value: String)

    data class UserData(
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val phone: String,
    )

    data class State(
        val isContinueAvailable: Boolean,
        val userInfo: UserInfo,
    ) {
        data class UserInfo(
            val firstName: FirstNameField,
            val lastName: LastNameField,
            val middleName: MiddleNameField,
            val phone: PhoneField,
        ) {
            data class FirstNameField(
                val value: String,
            )

            data class LastNameField(
                val value: String,
            )

            data class MiddleNameField(
                val value: String,
            )

            data class PhoneField(
                val value: String,
                val isEditable: Boolean,
            )
        }
    }
}