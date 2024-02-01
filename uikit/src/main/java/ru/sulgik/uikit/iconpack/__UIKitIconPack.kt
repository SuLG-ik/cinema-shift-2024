package ru.sulgik.uikit.iconpack

import androidx.compose.ui.graphics.vector.ImageVector
import ru.sulgik.uikit.iconpack.uikiticonpack.BackButton
import ru.sulgik.uikit.iconpack.uikiticonpack.ImageNotFound
import kotlin.collections.List as ____KtList

public object UIKitIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val UIKitIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(ImageNotFound, BackButton)
    return __AllIcons!!
  }
