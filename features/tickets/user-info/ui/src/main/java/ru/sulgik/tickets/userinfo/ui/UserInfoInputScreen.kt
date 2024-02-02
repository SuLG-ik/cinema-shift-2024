package ru.sulgik.tickets.userinfo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitOutlineTextField
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.field.UIKitPhoneTextField
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens


data class UserInfo(
    val name: NameField,
    val lastName: LastNameField,
    val middleName: MiddleNameField,
    val phone: PhoneField,
) {
    data class NameField(
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

@Composable
fun UserInfoInputScreen(
    info: UserInfo,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onFirstNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onPhoneInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.place_selection))
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isContinueAvailable,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 }
            ) {
                UIKitContainedButton(
                    onClick = onContinue,
                    largeCorners = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
                ) {
                    Text(text = stringResource(R.string.continue_button))
                }
            }
        },
        modifier = modifier,
    ) {
        UserInfoInput(
            info = info,
            onNameInput = onFirstNameInput,
            onLastNameInput = onLastNameInput,
            onMiddleNameInput = onMiddleNameInput,
            onPhoneInput = onPhoneInput,
            modifier = Modifier
                .padding(it)
                .padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun UserInfoInput(
    info: UserInfo,
    onNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onPhoneInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
            verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        ) {
            Text(text = "Данные карты", style = MaterialTheme.typography.titleLarge)
            UserInfoFields(
                info = info,
                onNameInput = onNameInput,
                onLastNameInput = onLastNameInput,
                onMiddleNameInput = onMiddleNameInput,
                onPhoneInput = onPhoneInput,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun UserInfoFields(
    info: UserInfo,
    onNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onPhoneInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier,
    ) {
        NameField(
            field = info.name,
            onChanged = onNameInput,
            modifier = Modifier.fillMaxWidth(),
        )
        LastNameField(
            field = info.lastName,
            onChanged = onLastNameInput,
            modifier = Modifier.fillMaxWidth(),
        )
        MiddleNameField(
            field = info.middleName,
            onChanged = onMiddleNameInput,
            modifier = Modifier.fillMaxWidth(),
        )
        PhoneField(
            field = info.phone,
            onChanged = onPhoneInput,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun NameField(
    field: UserInfo.NameField,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UIKitOutlineTextField(
        title = "Имя*y",
        value = field.value,
        onValueChange = onChanged,
        modifier = modifier,
    )
}

@Composable
private fun LastNameField(
    field: UserInfo.LastNameField,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UIKitOutlineTextField(
        title = "Фамилия*",
        value = field.value,
        onValueChange = onChanged,
        modifier = modifier,
    )
}

@Composable
private fun MiddleNameField(
    field: UserInfo.MiddleNameField,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UIKitOutlineTextField(
        title = "Отчество",
        value = field.value,
        onValueChange = onChanged,
        modifier = modifier,
    )
}

@Composable
private fun PhoneField(
    field: UserInfo.PhoneField,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    UIKitPhoneTextField(
        phone = field.value,
        onPhoneChanged = onChanged,
        modifier = modifier,
    )
}