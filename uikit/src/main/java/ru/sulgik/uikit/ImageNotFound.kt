package ru.sulgik.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import ru.sulgik.uikit.iconpack.UIKitIconPack
import ru.sulgik.uikit.iconpack.uikiticonpack.ImageNotFound
import ru.sulgik.uikit.tokens.UIKitShapeTokens

@Composable
fun ImageNotFound(
    modifier: Modifier = Modifier,
    shape: Shape = UIKitShapeTokens.CornerMedium,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainer,
) {
    Box(
        modifier
            .background(backgroundColor, shape = shape)
            .clip(shape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            UIKitIconPack.ImageNotFound,
            contentDescription = stringResource(R.string.image_not_found),
            modifier = Modifier.fillMaxWidth(0.5f),
        )
    }
}