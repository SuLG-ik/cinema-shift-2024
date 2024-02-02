package ru.sulgik.card.ui

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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ru.sulgik.card.component.R
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitOutlineTextField
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens


data class Card(
    val cardNumber: CardNumberField,
    val date: CardDateField,
    val ccv: CardCCVField,
) {

    data class CardNumberField(
        val value: String,
        val error: Error?,
    )

    data class CardDateField(
        val value: String,
        val error: Error?,
    )

    data class CardCCVField(
        val value: String,
        val error: Error?,
    )

    sealed interface Error {
        data object IncorrectLength : Error
        data object IncorrectValue : Error
    }
}

@Composable
fun CardInputScreen(
    card: Card,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onCardNumberInput: (String) -> Unit,
    onCardDateInput: (String) -> Unit,
    onCardCCVInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.top_bar))
                },
                onBack = onBack,
            )
        },
        modifier = modifier,
    ) {
        CardInput(
            card = card,
            isContinueAvailable = isContinueAvailable,
            onContinue = onContinue,
            onCardNumberInput = onCardNumberInput,
            onCardDateInput = onCardDateInput,
            onCardCCVInput = onCardCCVInput,
            modifier = Modifier
                .padding(it)
                .padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun CardInput(
    card: Card,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onCardNumberInput: (String) -> Unit,
    onCardDateInput: (String) -> Unit,
    onCardCCVInput: (String) -> Unit,
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
        Text(text = stringResource(R.string.card_data), style = MaterialTheme.typography.titleLarge)
        CardFields(
            card = card,
            onCardNumberInput = onCardNumberInput,
            onCardDateInput = onCardDateInput,
            onCardCCVInput = onCardCCVInput,
            onContinue = onContinue,
            modifier = Modifier.fillMaxWidth()
        )
        ContinueButton(
            isContinueAvailable = isContinueAvailable,
            onContinue = onContinue,
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
fun CardFields(
    card: Card,
    onCardNumberInput: (String) -> Unit,
    onCardDateInput: (String) -> Unit,
    onCardCCVInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
        modifier = modifier,
    ) {
        CardNumberField(
            field = card.cardNumber,
            onInput = onCardNumberInput,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
        ) {
            CardDateField(
                field = card.date,
                onInput = onCardDateInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
            )
            CardCCVField(
                field = card.ccv,
                onInput = onCardCCVInput,
                onContinue = onContinue,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
            )
        }
    }
}

@Composable
fun CardNumberField(
    field: Card.CardNumberField,
    onInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    TitledOutlineTextField(
        title = stringResource(R.string.card_number),
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = stringResource(R.string.card_number_placeholder))
        },
        errorText = {
            DefaultError(
                error = field.error,
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        isError = field.error != null,
        visualTransformation = CardNumberVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun CardCCVField(
    field: Card.CardCCVField,
    onInput: (String) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    TitledOutlineTextField(
        title = stringResource(R.string.card_ccv),
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = stringResource(R.string.card_ccv_placeholder))
        },
        errorText = {
            DefaultError(
                error = field.error,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                focusManager.clearFocus()
                onContinue()
            }
        ),
        isError = field.error != null,
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun CardDateField(
    field: Card.CardDateField,
    onInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    TitledOutlineTextField(
        title = "Срок*",
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = stringResource(R.string.card_date_placeholder))
        },
        errorText = {
            DefaultError(
                error = field.error,
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.clearFocus()
            }
        ),
        isError = field.error != null,
        visualTransformation = DateVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun DefaultError(
    error: Card.Error?,
    modifier: Modifier = Modifier
) {
    if (error != null)
        Text(text = getDefaultErrorText(error), modifier = modifier)
}

@Composable
fun getDefaultErrorText(error: Card.Error): String {
    return when (error) {
        Card.Error.IncorrectLength -> stringResource(R.string.required_field_error)
        Card.Error.IncorrectValue -> stringResource(R.string.incorrect_value_error)
    }
}

@Composable
fun TitledOutlineTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: @Composable() (() -> Unit)? = null,
    isError: Boolean,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    UIKitOutlineTextField(
        title = title,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        visualTransformation = visualTransformation,
        errorText = errorText,
        isError = isError,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
    )
}