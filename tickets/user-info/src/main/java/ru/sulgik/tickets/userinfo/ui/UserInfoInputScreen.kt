package ru.sulgik.tickets.userinfo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.sulgik.core.validation.user.UserInputError
import ru.sulgik.tickets.userinfo.domain.entity.UserInfo
import ru.sulgik.tickets.userinfo.R
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitOutlineTextField
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.field.UIKitPhoneTextField
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens

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
                    Text(text = stringResource(R.string.user_info_input_top_bar))
                },
                onBack = onBack,
            )
        },
        modifier = modifier,
    ) {
        UserInfoInput(
            info = info,
            isContinueAvailable = isContinueAvailable,
            onContinue = onContinue,
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
fun ColumnScope.ContinueButton(
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
) {
    AnimatedVisibility(
        visible = isContinueAvailable,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
        modifier = Modifier
    ) {
        UIKitContainedButton(
            onClick = onContinue,
            largeCorners = false,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.continue_button))
        }
    }
}

@Composable
fun UserInfoInput(
    info: UserInfo,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onPhoneInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = UIKitShapeTokens.CornerMedium
            )
            .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
    ) {
        Text(
            text = stringResource(R.string.user_info_input_title),
            style = MaterialTheme.typography.titleLarge
        )
        UserInfoFields(
            info = info,
            onNameInput = onNameInput,
            onLastNameInput = onLastNameInput,
            onMiddleNameInput = onMiddleNameInput,
            onPhoneInput = onPhoneInput,
            onContinue = onContinue,
            modifier = Modifier.fillMaxWidth(),
        )
        ContinueButton(
            isContinueAvailable = isContinueAvailable, onContinue = onContinue
        )
    }
}

@Composable
fun UserInfoFields(
    info: UserInfo,
    onNameInput: (String) -> Unit,
    onLastNameInput: (String) -> Unit,
    onMiddleNameInput: (String) -> Unit,
    onPhoneInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier,
    ) {
        NameField(
            field = info.firstName,
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
            onContinue = onContinue,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun NameField(
    field: UserInfo.FirstNameField,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    UIKitOutlineTextField(
        title = stringResource(R.string.field_first_name),
        value = field.value,
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
    val focusManager = LocalFocusManager.current
    UIKitOutlineTextField(
        title = stringResource(R.string.field_last_name),
        value = field.value,
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
    val focusManager = LocalFocusManager.current
    UIKitOutlineTextField(
        title = stringResource(R.string.field_middle_name),
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
fun DefaultError(error: UserInputError?, modifier: Modifier = Modifier) {
    if (error != null)
        Text(
            text = textForError(error),
            modifier = modifier
        )
}

@Composable
fun PhoneError(error: UserInputError?, modifier: Modifier = Modifier) {
    if (error != null)
        Text(
            text = textForPhoneError(error),
            modifier = modifier
        )
}

@Composable
fun textForError(error: UserInputError): String {
    return when (error) {
        UserInputError.DifferentLanguages -> "Нельзя смешивать латиницу и кирилицу"
        UserInputError.IncorrectInput -> "Можно использовать только алфавиты кирилицы и латиницы"
        UserInputError.IncorrectLength -> "Поле не может быть пустым"
    }
}


@Composable
fun textForPhoneError(error: UserInputError): String {
    return when (error) {
        UserInputError.IncorrectInput -> "Неправильный номер телефона"
        UserInputError.IncorrectLength -> "Введите номер телефона"
        else -> ""
    }
}

@Composable
private fun PhoneField(
    field: UserInfo.PhoneField,
    onChanged: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    UIKitPhoneTextField(
        phone = field.value,
        onPhoneChanged = onChanged,
        errorText = {
            PhoneError(field.error)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go,
            keyboardType = KeyboardType.Phone,
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                focusManager.clearFocus()
                onContinue()
            }
        ),
        isError = field.error != null,
        modifier = modifier,
    )
}