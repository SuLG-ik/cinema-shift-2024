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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
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
    onNewCode: () -> Unit,
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
            onNewCode = onNewCode,
            modifier = Modifier
                .padding(it)
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun CodeField(
    field: LoginInput.CodeField,
    enabled: Boolean,
    onCodeInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = Unit, block = {
        focusRequester.requestFocus()
    })
    UIKitOutlineTextField(
        title = stringResource(R.string.one_time_code),
        value = field.value,
        onValueChange = onCodeInput,
        enabled = enabled,
        modifier = modifier.focusRequester(focusRequester),
    )
}

@Composable
fun rememberNewCodeTimeOut(): Long {
    var endTime by rememberSaveable {
        mutableLongStateOf(0)
    }
    var restartTime by remember { mutableLongStateOf(NEW_CODE_DELAY) }
    LaunchedEffect(key1 = Unit, block = {
        if (endTime == 0L)
            endTime = withFrameMillis { it + NEW_CODE_DELAY }
        while (true) {
            withFrameMillis {
                restartTime = maxOf(endTime - it, 0)
            }
            delay(1000)
        }
    })
    return restartTime
}

@Composable
fun PhoneInput(
    input: LoginInput,
    onPhoneInput: (String) -> Unit,
    onCodeInput: (String) -> Unit,
    onContinue: () -> Unit,
    onNewCode: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.phone_input_description),
            style = MaterialTheme.typography.bodyLarge,
        )
        PhoneField(
            field = input.phone,
            onPhoneInput = onPhoneInput,
            enabled = input.step == LoginInput.Step.PHONE_INPUT,
            modifier = Modifier.fillMaxWidth(),
        )
        AnimatedVisibility(visible = input.step == LoginInput.Step.CODE_INPUT) {
            CodeField(
                field = input.code,
                onCodeInput = onCodeInput,
                enabled = input.step == LoginInput.Step.CODE_INPUT,
                modifier = Modifier.fillMaxWidth()
            )
        }
        ContinueButton(
            step = input.step,
            isLoading = input.isLoading,
            isContinueAvailable = input.isContinueAvailable,
            onContinue = onContinue,
            modifier = Modifier.fillMaxWidth()
        )
        AnimatedVisibility(visible = !input.isLoading && input.step == LoginInput.Step.CODE_INPUT) {
            NewCodeButton(onNewCode = onNewCode)
        }
    }
}

@Composable
fun NewCodeButton(
    onNewCode: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val newCodeDelay = rememberNewCodeTimeOut() / 1000
    if (newCodeDelay > 0)
        Text(
            text = "Запросить новый код можно через ${newCodeDelay}",
            textAlign = TextAlign.Center,
            modifier = modifier,
        )
    else
        TextButton(onClick = onNewCode, modifier = modifier) {
            Text(
                text = "Запросить новый код",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
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
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    UIKitPhoneTextField(
        phone = field.value,
        onPhoneChanged = onPhoneInput,
        enabled = enabled,
        modifier = modifier,
    )
}

const val NEW_CODE_DELAY = 120L * 1000