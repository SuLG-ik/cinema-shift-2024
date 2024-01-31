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

public val UIKitIconPack.BackButton: ImageVector
    get() {
        if (_backButton != null) {
            return _backButton!!
        }
        _backButton = Builder(name = "BackButton", defaultWidth = 12.0.dp, defaultHeight = 20.0.dp,
                viewportWidth = 12.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF3E3E3E)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(9.4874f, 19.2375f)
                curveTo(9.8799f, 19.63f, 10.5172f, 19.6272f, 10.9062f, 19.2312f)
                curveTo(11.2903f, 18.8402f, 11.2876f, 18.2126f, 10.8999f, 17.825f)
                lineTo(3.025f, 9.95f)
                lineTo(10.8999f, 2.075f)
                curveTo(11.2876f, 1.6874f, 11.2903f, 1.0598f, 10.9062f, 0.6688f)
                curveTo(10.5172f, 0.2729f, 9.8799f, 0.27f, 9.4874f, 0.6625f)
                lineTo(0.9071f, 9.2429f)
                curveTo(0.5166f, 9.6334f, 0.5166f, 10.2666f, 0.9071f, 10.6571f)
                lineTo(9.4874f, 19.2375f)
                close()
            }
        }
        .build()
        return _backButton!!
    }

private var _backButton: ImageVector? = null
