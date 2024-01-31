package ru.sulgik.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.tokens.UIKitShapeTokens

@Composable
fun UIKitTabRow(
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    tabs: @Composable () -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier.clip(UIKitShapeTokens.CornerMedium),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(60.dp)
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = UIKitShapeTokens.CornerMedium
                        )
                )
            }
        },
        divider = {

        },
        tabs = tabs,
    )
}

@Composable
fun UIKitTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable ColumnScope.() -> Unit
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier.clip(UIKitShapeTokens.CornerMedium),
        enabled = enabled,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        interactionSource = interactionSource,
        content = content,
    )
}