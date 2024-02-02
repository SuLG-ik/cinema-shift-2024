package ru.sulgik.tickets.userinfo.presentation

import com.arkivanov.mvikotlin.core.store.Store


interface UserInfoInputStore :
    Store<UserInfoInputStore.Intent, UserInfoInputStore.State, UserInfoInputStore.Label> {

    sealed interface Intent {
        data class FirstNameInput(val value: String) : Intent
        data class LastNameInput(val value: String) : Intent
        data class MiddleNameInput(val value: String) : Intent
        data class PhoneInput(val value: String) : Intent
    }

    data class State(
        val isContinueAvailable: Boolean = false,
        val userInfo: UserInfo = UserInfo(),
    ) {
        data class UserInfo(
            val firstName: FirstNameField = FirstNameField(),
            val lastName: LastNameField = LastNameField(),
            val middleName: MiddleNameField = MiddleNameField(),
            val phone: PhoneField = PhoneField(),
        ) {

            sealed interface Error {
                data object IncorrectLength : Error
                data object DifferentLanguages : Error
                data object IncorrectInput : Error
            }

            data class FirstNameField(
                val value: String = "",
                val error: Error? = Error.IncorrectLength,
            ) {
            }

            data class LastNameField(
                val value: String = "",
                val error: Error? = Error.IncorrectLength,
            )

            data class MiddleNameField(
                val value: String = "",
                val error: Error? = null,
            )

            data class PhoneField(
                val value: String = "",
                val isEditable: Boolean = true,
                val error: Error? = Error.IncorrectLength,
            )
        }

    }

    sealed interface Label

}