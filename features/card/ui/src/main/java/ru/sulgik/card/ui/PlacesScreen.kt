package ru.sulgik.card.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
    )

    data class CardDateField(
        val value: String,
    )

    data class CardCCVField(
        val value: String,
    )
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
        CardInput(
            card = card,
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
    onCardNumberInput: (String) -> Unit,
    onCardDateInput: (String) -> Unit,
    onCardCCVInput: (String) -> Unit,
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
            CardFields(
                card = card,
                onCardNumberInput = onCardNumberInput,
                onCardDateInput = onCardDateInput,
                onCardCCVInput = onCardCCVInput,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CardFields(
    card: Card,
    onCardNumberInput: (String) -> Unit,
    onCardDateInput: (String) -> Unit,
    onCardCCVInput: (String) -> Unit,
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
    TitledOutlineTextField(
        title = "Номер карты*",
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = "0000 0000")
        },
        visualTransformation = CardNumberVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun CardCCVField(
    field: Card.CardCCVField,
    onInput: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TitledOutlineTextField(
        title = "CCV*",
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = "0000")
        },
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
    TitledOutlineTextField(
        title = "Срок*",
        value = field.value,
        onValueChange = onInput,
        placeholder = {
            Text(text = "00/00")
        },
        visualTransformation = DateVisualTransformation(),
        modifier = modifier,
    )
}

@Composable
fun TitledOutlineTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
    ),
) {
    UIKitOutlineTextField(
        title = title,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        visualTransformation = visualTransformation,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier,
    )
}