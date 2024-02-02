package ru.sulgik.tickets.userinfo.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo


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
    }

    sealed interface Label

}