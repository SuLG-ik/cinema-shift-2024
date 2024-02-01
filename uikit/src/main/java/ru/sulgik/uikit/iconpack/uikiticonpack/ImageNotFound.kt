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

public val UIKitIconPack.ImageNotFound: ImageVector
    get() {
        if (_imageNotFound != null) {
            return _imageNotFound!!
        }
        _imageNotFound = Builder(name = "ImageNotFound", defaultWidth = 512.0.dp, defaultHeight =
                512.0.dp, viewportWidth = 64.0f, viewportHeight = 64.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(54.167f, 41.247f)
                arcToRelative(4.992f, 4.992f, 0.0f, false, true, -6.92f, 6.92f)
                close()
                moveTo(50.0f, 39.0f)
                arcToRelative(4.992f, 4.992f, 0.0f, false, false, -4.167f, 7.753f)
                lineToRelative(6.92f, -6.92f)
                arcTo(4.966f, 4.966f, 0.0f, false, false, 50.0f, 39.0f)
                close()
                moveTo(61.0f, 44.0f)
                arcToRelative(10.996f, 10.996f, 0.0f, false, true, -21.809f, 2.0f)
                lineTo(6.0f, 46.0f)
                arcToRelative(3.003f, 3.003f, 0.0f, false, true, -3.0f, -3.0f)
                lineTo(3.0f, 12.0f)
                arcToRelative(3.003f, 3.003f, 0.0f, false, true, 3.0f, -3.0f)
                horizontalLineToRelative(42.0f)
                arcToRelative(3.003f, 3.003f, 0.0f, false, true, 3.0f, 3.0f)
                verticalLineToRelative(21.05f)
                arcTo(11.011f, 11.011f, 0.0f, false, true, 61.0f, 44.0f)
                close()
                moveTo(5.0f, 12.0f)
                verticalLineToRelative(30.399f)
                lineToRelative(14.393f, -17.99f)
                arcToRelative(2.015f, 2.015f, 0.0f, false, true, 1.616f, -0.75f)
                arcToRelative(1.983f, 1.983f, 0.0f, false, true, 1.573f, 0.836f)
                lineToRelative(5.42f, 7.089f)
                lineToRelative(5.501f, -5.501f)
                arcToRelative(1.974f, 1.974f, 0.0f, false, true, 1.524f, -0.583f)
                arcToRelative(2.004f, 2.004f, 0.0f, false, true, 1.451f, 0.747f)
                lineToRelative(7.685f, 8.446f)
                arcTo(10.91f, 10.91f, 0.0f, false, true, 49.0f, 33.05f)
                lineTo(49.0f, 12.0f)
                arcToRelative(1.001f, 1.001f, 0.0f, false, false, -1.0f, -1.0f)
                lineTo(6.0f, 11.0f)
                arcToRelative(1.001f, 1.001f, 0.0f, false, false, -1.0f, 1.0f)
                close()
                moveTo(20.974f, 25.684f)
                lineTo(6.282f, 44.0f)
                horizontalLineToRelative(28.694f)
                close()
                moveTo(42.569f, 35.914f)
                lineTo(34.958f, 27.545f)
                lineTo(29.231f, 33.191f)
                lineTo(37.495f, 44.0f)
                lineTo(39.0f, 44.0f)
                arcToRelative(10.96f, 10.96f, 0.0f, false, true, 3.569f, -8.087f)
                close()
                moveTo(57.0f, 44.0f)
                arcToRelative(7.0f, 7.0f, 0.0f, true, false, -7.0f, 7.0f)
                arcToRelative(7.008f, 7.008f, 0.0f, false, false, 7.0f, -7.0f)
                close()
                moveTo(35.0f, 18.0f)
                arcToRelative(5.0f, 5.0f, 0.0f, true, true, 5.0f, 5.0f)
                arcToRelative(5.006f, 5.006f, 0.0f, false, true, -5.0f, -5.0f)
                close()
                moveTo(37.0f, 18.0f)
                arcToRelative(3.0f, 3.0f, 0.0f, true, false, 3.0f, -3.0f)
                arcToRelative(3.003f, 3.003f, 0.0f, false, false, -3.0f, 3.0f)
                close()
            }
        }
        .build()
        return _imageNotFound!!
    }

private var _imageNotFound: ImageVector? = null
