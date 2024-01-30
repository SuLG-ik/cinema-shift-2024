package ru.sulgik.uikit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun UIKitContainedButtonLargeCornersPreview() {
    UIKitContainedButton(
        onClick = {},
        largeCorners = true,
    ) {
        Text("Button")
    }
}

@Preview
@Composable
fun UIKitContainedButtonPreview() {
    UIKitContainedButton(
        onClick = {},
        largeCorners = false,
    ) {
        Text("Button")
    }
}