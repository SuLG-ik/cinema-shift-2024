package ru.sulgik.uikit

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.iconpack.UIKitIconPack
import ru.sulgik.uikit.iconpack.uikiticonpack.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIKitTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            ProvideTextStyle(value = MaterialTheme.typography.titleLarge) {
                title()
            }
        },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        UIKitIconPack.BackButton,
                        contentDescription = stringResource(R.string.go_back),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        modifier = modifier,
    )
}