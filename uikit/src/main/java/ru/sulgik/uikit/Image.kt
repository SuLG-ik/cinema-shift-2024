package ru.sulgik.uikit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage

@Composable
fun UIKitRemoteImage(
    url: String,
    modifier: Modifier,
) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
    )
}