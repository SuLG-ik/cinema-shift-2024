package ru.sulgik.uikit

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.VectorConfig
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.iconpack.UIKitIconPack
import ru.sulgik.uikit.iconpack.uikiticonpack.RatingStar
import ru.sulgik.uikit.tokens.UIKitColorTokens

@Composable
fun UIKitRatingStars(
    value: Float,
    fillColor: Color = UIKitColorTokens.RatingStarsFillColor,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier,
    ) {
        repeat(5) {
            GradientIcon(
                image = UIKitIconPack.RatingStar,
                fill = fillByIndexAndValue(value, it),
                fillColor = fillColor,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

fun fillByIndexAndValue(value: Float, index: Int): Float {
    return ((value - index * 2) / 2).coerceIn(0.0f, 1f)
}

@Composable
fun gradientByFill(
    fill: Float,
    fillColor: Color = Color.Yellow,
    noFillColor: Color = LocalContentColor.current.copy(alpha = 0.25f),
): Brush {
    return Brush.horizontalGradient(
        0.0f to fillColor,
        fill - 0.01f to fillColor,
        fill to noFillColor
    )
}

class GradientConfig(private val brush: Brush) : VectorConfig {
    override fun <T> getOrDefault(property: VectorProperty<T>, defaultValue: T): T {
        return when (property) {
            is VectorProperty.Fill -> brush as T
            else -> super.getOrDefault(property, defaultValue)
        }
    }
}

@Composable
fun GradientIcon(
    image: ImageVector,
    fill: Float,
    modifier: Modifier = Modifier,
    fillColor: Color = Color.Yellow,
    noFillColor: Color = LocalContentColor.current.copy(alpha = 0.25f),
) {
    val painter = rememberVectorPainter(image = image)
    Canvas(modifier = modifier, onDraw = {
        with(painter) {
            draw(size, colorFilter = ColorFilter.tint(noFillColor))
        }
        withTransform({
            this.clipRect(right = size.width * fill)
        }) {
            with(painter) {
                draw(size, colorFilter = ColorFilter.tint(fillColor))
            }
        }
    })
}

@Composable
fun rememberVectorPainter(image: ImageVector, configs: Map<String, VectorConfig>): VectorPainter {
    return rememberVectorPainter(defaultWidth = image.defaultWidth,
        defaultHeight = image.defaultHeight,
        viewportWidth = image.viewportWidth,
        viewportHeight = image.viewportHeight,
        name = image.name,
        tintColor = image.tintColor,
        tintBlendMode = image.tintBlendMode,
        autoMirror = true,
        content = { _, _ -> RenderVectorGroup(group = image.root, configs = configs) }
    )
}
