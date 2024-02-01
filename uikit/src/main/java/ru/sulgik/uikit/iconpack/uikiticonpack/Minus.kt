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

public val UIKitIconPack.Minus: ImageVector
    get() {
        if (_minus != null) {
            return _minus!!
        }
        _minus = Builder(name = "Minus", defaultWidth = 18.0.dp, defaultHeight = 6.0.dp,
                viewportWidth = 18.0f, viewportHeight = 6.0f).apply {
            path(fill = SolidColor(Color(0xFF637083)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.9546f, 5.034f)
                verticalLineTo(0.9658f)
                horizontalLineTo(17.0455f)
                verticalLineTo(5.034f)
                horizontalLineTo(0.9546f)
                close()
            }
        }
        .build()
        return _minus!!
    }

private var _minus: ImageVector? = null
