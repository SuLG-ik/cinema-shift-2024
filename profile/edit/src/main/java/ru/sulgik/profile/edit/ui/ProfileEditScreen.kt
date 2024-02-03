package ru.sulgik.profile.edit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.sulgik.profile.edit.R
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens


@Composable
fun ProfileEditScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.confirmation))
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            UIKitContainedButton(
                onClick = onContinue,
                largeCorners = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
            ) {
                Text(text = stringResource(R.string.continue_button))
            }
        },
        modifier = modifier,
    ) {
        OrderCard(
            modifier = Modifier
                .padding(it)
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
    }
}

@Composable
fun OrderCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(UIKitPaddingDefaultTokens.DefaultContentPadding),
            verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
        ) {


        }
    }
}
