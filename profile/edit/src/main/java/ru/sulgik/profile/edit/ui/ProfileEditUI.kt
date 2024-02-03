package ru.sulgik.profile.edit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.profile.edit.component.Confirmation

@Composable
fun ProfileEditScreenUI(
    component: Confirmation,
    modifier: Modifier = Modifier,
) {
    ProfileEditScreen(
        onContinue = component::onContinue,
        onBack = component::onBack,
        modifier = modifier,
    )
}