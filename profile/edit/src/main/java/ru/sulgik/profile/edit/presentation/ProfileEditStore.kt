package ru.sulgik.profile.edit.presentation

import com.arkivanov.mvikotlin.core.store.Store
import ru.sulgik.profile.edit.domain.entity.UserProfileInput

interface ProfileEditStore :
    Store<ProfileEditStore.Intent, ProfileEditStore.State, ProfileEditStore.Label> {

    sealed interface Intent {
        data class FirstNameInput(val value: String): Intent
        data class LastNameInput(val value: String): Intent
        data class MiddleNameInput(val value: String): Intent
        data class EmailInput(val value: String): Intent
        data class CityInput(val value: String): Intent
    }

    data class State(
        val isLoading: Boolean = true,
        val isContinueAvailable: Boolean = false,
        val profile: UserProfileInput? = null,
    )

    sealed interface Label {
        data object Unauthorized: Label
    }

}