package ru.sulgik.profile.edit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.profile.edit.component.ProfileEdit

@Composable
fun ProfileEditUI(
    component: ProfileEdit,
    modifier: Modifier = Modifier,
) {
    val input by component.userProfileInput.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val isLoading by component.isLoading.subscribeAsState()
    ProfileEditScreen(
        modifier = modifier,
        input = input.value,
        isContinueAvailable = isContinueAvailable,
        isLoading = isLoading,
        onFirstNameInput = component::onFirstNameInput,
        onLastNameInput = component::onLastNameInput,
        onMiddleNameInput = component::onMiddleNameInput,
        onEmailInput = component::onEmailNameInput,
        onCityInput = component::onCityNameInput,
        onContinue = component::onContinue,
    )
}