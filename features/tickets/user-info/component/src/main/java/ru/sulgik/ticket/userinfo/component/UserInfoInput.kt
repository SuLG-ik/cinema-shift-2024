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
                val error: Error?,
            )

            data class LastNameField(
                val value: String,
                val error: Error?,
            )

            data class MiddleNameField(
                val value: String,
                val error: Error?,
            )

            data class PhoneField(
                val value: String,
                val isEditable: Boolean,
                val error: Error?,
            )


            sealed interface Error {
                data object IncorrectLength : Error
                data object DifferentLanguages : Error
                data object IncorrectInput : Error
            }
        }
    }
}