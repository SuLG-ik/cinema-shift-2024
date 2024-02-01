package ru.sulgik.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.RatingStar: ImageVector
    get() {
        if (_ratingStar != null) {
            return _ratingStar!!
        }
        _ratingStar = Builder(name = "RatingStar", defaultWidth = 16.0.dp, defaultHeight = 15.0.dp,
                viewportWidth = 16.0f, viewportHeight = 15.0f).apply {
            path(fill = SolidColor(Color(0xFF1D1D1B)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.6f, 0.8f)
                curveToRelative(0.2f, -0.3f, 0.7f, -0.3f, 0.8f, 0.0f)
                lineToRelative(2.0f, 3.9f)
                curveToRelative(0.1f, 0.1f, 0.2f, 0.2f, 0.4f, 0.3f)
                lineToRelative(4.4f, 0.6f)
                curveToRelative(0.4f, 0.1f, 0.5f, 0.5f, 0.3f, 0.8f)
                lineToRelative(-3.2f, 3.1f)
                curveToRelative(-0.1f, 0.1f, -0.2f, 0.3f, -0.1f, 0.4f)
                lineToRelative(0.7f, 4.3f)
                curveToRelative(0.1f, 0.4f, -0.3f, 0.7f, -0.7f, 0.5f)
                lineToRelative(-3.9f, -2.0f)
                curveToRelative(-0.1f, -0.1f, -0.3f, -0.1f, -0.4f, 0.0f)
                lineToRelative(-3.9f, 2.0f)
                curveToRelative(-0.3f, 0.2f, -0.7f, -0.1f, -0.7f, -0.5f)
                lineToRelative(0.7f, -4.3f)
                curveToRelative(0.0f, -0.2f, 0.0f, -0.3f, -0.1f, -0.4f)
                lineTo(0.6f, 6.4f)
                curveTo(0.4f, 6.1f, 0.5f, 5.6f, 0.9f, 5.6f)
                lineTo(5.3f, 5.0f)
                curveToRelative(0.2f, 0.0f, 0.3f, -0.1f, 0.4f, -0.3f)
                lineTo(7.6f, 0.8f)
                close()
            }
        }
        .build()
        return _ratingStar!!
    }

private var _ratingStar: ImageVector? = null
