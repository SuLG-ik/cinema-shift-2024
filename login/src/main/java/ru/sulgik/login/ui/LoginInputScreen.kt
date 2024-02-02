package ru.sulgik.login.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sulgik.login.R
import ru.sulgik.login.domain.entity.LoginInput
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitOutlineTextField
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.field.UIKitPhoneTextField
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens

@Composable
fun LoginInputScreen(
    input: LoginInput,
    onPhoneInput: (String) -> Unit,
    onCodeInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(title = { Text(text = stringResource(R.string.login_top_bar)) })
        },
        modifier = modifier
    ) {
        PhoneInput(
            input = input,
            onPhoneInput = onPhoneInput,
            onCodeInput = onCodeInput,
            onContinue = onContinue,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Composable
private fun CodeField(
    field: LoginInput.CodeField,
    onCodeInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitOutlineTextField(
        title = stringResource(R.string.one_time_code),
        value = field.value,
        onValueChange = onCodeInput,
        enabled = false,
        modifier = modifier,
    )
}

@Composable
fun PhoneInput(
    input: LoginInput,
    onPhoneInput: (String) -> Unit,
    onCodeInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
    ) {
        Text(text = stringResource(R.string.phone_input_description))
        PhoneField(
            field = input.phone,
            onPhoneInput = onPhoneInput,
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(visible = input.step == LoginInput.Step.CODE_INPUT) {
            CodeField(
                field = input.code,
                onCodeInput = onCodeInput,
                modifier = Modifier.fillMaxWidth()
            )
        }
        ContinueButton(
            step = input.step,
            isLoading = input.isLoading,
            isContinueAvailable = input.isContinueAvailable,
            onContinue = onContinue,
        )
    }
}

@Composable
private fun ContinueButton(
    step: LoginInput.Step,
    isContinueAvailable: Boolean,
    isLoading: Boolean,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitContainedButton(
        onClick = onContinue,
        enabled = isContinueAvailable,
        modifier = modifier,
    ) {
        Row {
            AnimatedVisibility(visible = isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
            AnimatedContent(targetState = step, label = "continue_button_text") {
                when (it) {
                    LoginInput.Step.PHONE_INPUT -> Text(text = stringResource(id = R.string.continue_button))
                    LoginInput.Step.CODE_INPUT -> Text(text = stringResource(id = R.string.login_button))
                }
            }
        }
    }
}

@Composable
private fun PhoneField(
    field: LoginInput.PhoneField,
    onPhoneInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    UIKitPhoneTextField(
        phone = field.value,
        onPhoneChanged = onPhoneInput,
        modifier = modifier,
    )
}