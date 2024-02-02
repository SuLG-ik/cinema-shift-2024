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
            data class FirstNameField(
                val value: String = "",
            )

            data class LastNameField(
                val value: String = "",
            )

            data class MiddleNameField(
                val value: String = "",
            )

            data class PhoneField(
                val value: String = "",
                val isEditable: Boolean = true,
            )
        }

    }

    sealed interface Label

}