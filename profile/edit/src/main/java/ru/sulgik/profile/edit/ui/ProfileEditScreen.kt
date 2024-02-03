package ru.sulgik.profile.edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import ru.sulgik.core.validation.user.UserInputError
import ru.sulgik.profile.edit.R
import ru.sulgik.profile.edit.domain.entity.UserProfileInput
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitOutlineTextField
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.field.UIKitPhoneTextField
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens


@Composable
fun ProfileEditScreen(
    input: UserProfileInput?,
    isContinueAvailable: Boolean,
    onFirstNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onCityInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.profile_top_bar))
                },
            )
        },
        modifier = modifier,
    ) {
        if (input != null) {
            ProfileEditInput(
                input = input,
                isContinueAvailable = isContinueAvailable,
                onFirstNameInput = onFirstNameInput,
                onLastNameInput = onLastNameInput,
                onMiddleNameInput = onMiddleNameInput,
                onEmailInput = onEmailInput,
                onCityInput = onCityInput,
                onContinue = onContinue,
                modifier = Modifier
                    .padding(it)
                    .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
            )
        }
    }
}

@Composable
fun DefaultError(error: UserInputError?, modifier: Modifier = Modifier) {
    if (error != null)
        Text(
            text = textForError(error),
            modifier = modifier
        )
}


@Composable
fun textForError(error: UserInputError): String {
    return when (error) {
        UserInputError.DifferentLanguages -> stringResource(R.string.profile_error_different_languages)
        UserInputError.IncorrectInput -> stringResource(R.string.profile_error_incorrect_input)
        UserInputError.IncorrectLength -> stringResource(R.string.profile_error_incorrent_length)
    }
}

@Composable
fun Fields(
    input: UserProfileInput,
    onFirstNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onCityInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier,
    ) {
        PhoneField(
            field = input.phone,
            modifier = Modifier.fillMaxWidth(),
        )
        EditableField(
            title = stringResource(R.string.profile_firstname),
            field = input.firstName,
            onChanged = onFirstNameInput,
            modifier = Modifier.fillMaxWidth()
        )
        EditableField(
            title = stringResource(R.string.profile_lastname),
            field = input.lastName,
            onChanged = onLastNameInput,
            modifier = Modifier.fillMaxWidth()
        )
        EditableField(
            title = stringResource(R.string.profile_middlename),
            field = input.middleName,
            onChanged = onMiddleNameInput,
            modifier = Modifier.fillMaxWidth()
        )
        EditableField(
            title = stringResource(R.string.profile_email),
            field = input.email,
            onChanged = onEmailInput,
            modifier = Modifier.fillMaxWidth()
        )
        EditableField(
            title = stringResource(R.string.profile_city),
            field = input.city,
            onChanged = onCityInput,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EditableField(
    title: String,
    field: UserProfileInput.EditableField,
    onChanged: (String) -> Unit,
    modifier: Modifier,
) {
    val focusManager = LocalFocusManager.current
    UIKitOutlineTextField(
        title = title,
        value = field.value,
        onValueChange = onChanged,
        errorText = {
            DefaultError(
                field.error
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        singleLine = true,
        isError = field.error != null,
        modifier = modifier,
    )
}

@Composable
fun PhoneField(
    field: UserProfileInput.PhoneField,
    modifier: Modifier = Modifier,
) {
    UIKitPhoneTextField(
        phone = field.value,
        onPhoneChanged = {},
        enabled = false,
        modifier = modifier,
    )
}

@Composable
fun ProfileEditInput(
    isContinueAvailable: Boolean,
    input: UserProfileInput,
    onFirstNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onEmailInput: (String) -> Unit,
    onCityInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier
    ) {
        Fields(
            input = input,
            onFirstNameInput = onFirstNameInput,
            onLastNameInput = onLastNameInput,
            onMiddleNameInput = onMiddleNameInput,
            onEmailInput = onEmailInput,
            onCityInput = onCityInput,
            modifier = Modifier.fillMaxWidth(),
        )
        UIKitContainedButton(
            onClick = onContinue,
            largeCorners = false,
            enabled = isContinueAvailable,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_button))
        }
    }
}
