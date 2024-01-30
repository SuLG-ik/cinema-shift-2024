package ru.sulgik.uikit

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.uikit.UIKitButtonDefaults.containedButtonColors
import ru.sulgik.uikit.tokens.UIKitContainedButtonTokens
import ru.sulgik.uikit.tokens.UIKitShapeTokens
import ru.sulgik.uikit.tokens.toColor

object UIKitButtonDefaults {

    @Composable
    fun containedButtonColors(): ButtonColors {
        return ButtonDefaults.filledTonalButtonColors(
            containerColor = UIKitContainedButtonTokens.ContainerColor.toColor(),
            contentColor = UIKitContainedButtonTokens.ContentColor.toColor(),
        )
    }

}

@Composable
fun UIKitContainedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    largeCorners: Boolean = true,
    text: @Composable () -> Unit,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        shape = if (largeCorners) UIKitShapeTokens.CornerLarge else UIKitShapeTokens.CornerMedium,
        colors = containedButtonColors(),
    ) {
        text()
    }
}