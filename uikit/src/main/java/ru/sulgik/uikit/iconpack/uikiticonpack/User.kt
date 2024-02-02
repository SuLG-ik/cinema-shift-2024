package ru.sulgik.uikit.iconpack.uikiticonpack

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.iconpack.UIKitIconPack

public val UIKitIconPack.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(name = "User", defaultWidth = 24.0.dp, defaultHeight = 25.0.dp,
                viewportWidth = 24.0f, viewportHeight = 25.0f).apply {
            path(fill = SolidColor(Color(0xFF97A1AF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(16.0f, 8.0815f)
                curveTo(16.0f, 10.2907f, 14.2091f, 12.0815f, 12.0f, 12.0815f)
                curveTo(9.7909f, 12.0815f, 8.0f, 10.2907f, 8.0f, 8.0815f)
                curveTo(8.0f, 5.8724f, 9.7909f, 4.0815f, 12.0f, 4.0815f)
                curveTo(14.2091f, 4.0815f, 16.0f, 5.8724f, 16.0f, 8.0815f)
                close()
                moveTo(14.5f, 8.0815f)
                curveTo(14.5f, 9.4623f, 13.3807f, 10.5815f, 12.0f, 10.5815f)
                curveTo(10.6193f, 10.5815f, 9.5f, 9.4623f, 9.5f, 8.0815f)
                curveTo(9.5f, 6.7008f, 10.6193f, 5.5815f, 12.0f, 5.5815f)
                curveTo(13.3807f, 5.5815f, 14.5f, 6.7008f, 14.5f, 8.0815f)
                close()
            }
            path(fill = SolidColor(Color(0xFF97A1AF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = EvenOdd) {
                moveTo(4.0f, 17.8535f)
                verticalLineTo(20.0815f)
                horizontalLineTo(20.0f)
                verticalLineTo(17.8535f)
                curveTo(20.0f, 16.1862f, 18.9657f, 14.6937f, 17.4045f, 14.1082f)
                lineTo(17.2669f, 14.0566f)
                curveTo(13.8711f, 12.7832f, 10.1289f, 12.7832f, 6.7332f, 14.0566f)
                lineTo(6.5955f, 14.1082f)
                curveTo(5.0343f, 14.6937f, 4.0f, 16.1862f, 4.0f, 17.8535f)
                close()
                moveTo(7.2598f, 15.4611f)
                curveTo(10.316f, 14.315f, 13.684f, 14.315f, 16.7402f, 15.4611f)
                lineTo(16.8778f, 15.5127f)
                curveTo(17.8536f, 15.8786f, 18.5f, 16.8114f, 18.5f, 17.8535f)
                verticalLineTo(18.5815f)
                horizontalLineTo(5.5f)
                verticalLineTo(17.8535f)
                curveTo(5.5f, 16.8114f, 6.1464f, 15.8786f, 7.1222f, 15.5127f)
                lineTo(7.2598f, 15.4611f)
                close()
            }
        }
        .build()
        return _user!!
    }

private var _user: ImageVector? = null
