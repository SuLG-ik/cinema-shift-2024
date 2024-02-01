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

public val UIKitIconPack.Plus: ImageVector
    get() {
        if (_plus != null) {
            return _plus!!
        }
        _plus = Builder(name = "Plus", defaultWidth = 18.0.dp, defaultHeight = 18.0.dp,
                viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            path(fill = SolidColor(Color(0xFF141C24)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(6.966f, 17.0455f)
                verticalLineTo(0.9546f)
                horizontalLineTo(11.0341f)
                verticalLineTo(17.0455f)
                horizontalLineTo(6.966f)
                close()
                moveTo(0.9546f, 11.0341f)
                verticalLineTo(6.966f)
                horizontalLineTo(17.0455f)
                verticalLineTo(11.0341f)
                horizontalLineTo(0.9546f)
                close()
            }
        }
        .build()
        return _plus!!
    }

private var _plus: ImageVector? = null
