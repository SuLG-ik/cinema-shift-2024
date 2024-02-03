package ru.sulgik.profile.edit.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.ValueContainer
import ru.sulgik.profile.edit.domain.entity.UserProfileInput


interface ProfileEdit {

    val userProfileInput: Value<ValueContainer<UserProfileInput?>>

    val isContinueAvailable: Value<Boolean>

    fun onContinue()

    fun onFirstNameInput(value: String)

    fun onLastNameInput(value: String)

    fun onMiddleNameInput(value: String)

    fun onEmailNameInput(value: String)

    fun onCityNameInput(value: String)


}