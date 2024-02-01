package ru.sulgik.tickets.places.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import ru.sulgik.uikit.UIKitContainedButton
import ru.sulgik.uikit.UIKitTopBar
import ru.sulgik.uikit.iconpack.UIKitIconPack
import ru.sulgik.uikit.iconpack.uikiticonpack.Minus
import ru.sulgik.uikit.iconpack.uikiticonpack.Plus
import ru.sulgik.uikit.tokens.UIKitPaddingDefaultTokens


data class Place(
    val state: PlaceState,
    val price: Int,
    val position: Position,
) {

    data class Position(
        val row: Int,
        val column: Int,
    )

    enum class PlaceState {
        UNSELECTED, EXTERNAL_SELECTED, SELECTED
    }
}

@Composable
fun PlacesScreen(
    places: List<List<Place>>,
    selectedPlaces: List<Place>,
    totalCost: Int,
    isContinueAvailable: Boolean,
    onContinue: () -> Unit,
    onBack: () -> Unit,
    onSelect: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            UIKitTopBar(
                title = {
                    Text(text = stringResource(R.string.place_selection))
                },
                onBack = onBack,
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = isContinueAvailable,
                enter = fadeIn() + slideInVertically { it / 2 },
                exit = fadeOut() + slideOutVertically { it / 2 }
            ) {
                Column {
                    OrderInfo(
                        totalCost = totalCost,
                        selectedPlaces = selectedPlaces,
                    )
                    UIKitContainedButton(
                        onClick = onContinue,
                        largeCorners = false,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(UIKitPaddingDefaultTokens.DefaultContentPadding)
                    ) {
                        Text(text = stringResource(R.string.continue_button))
                    }
                }
            }
        },
        modifier = modifier,
    ) {
        PlaceSelecting(
            places = places,
            onSelect = onSelect,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        )
    }
}

@Composable
fun OrderInfo(
    totalCost: Int,
    selectedPlaces: List<Place>,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    LaunchedEffect(key1 = selectedPlaces.size, block = {
        scrollState.animateScrollTo(scrollState.maxValue)
    })
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .sizeIn(maxHeight = 150.dp)
                .verticalScroll(scrollState)
        ) {
            Text(text = stringResource(R.string.places), style = MaterialTheme.typography.bodySmall)
            selectedPlaces.forEach {
                OderItem(place = it, modifier = Modifier.fillMaxWidth())
            }
        }
        Text(text = stringResource(R.string.total_cost, totalCost), style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun OderItem(
    place: Place,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(
            R.string.places_row_column_price,
            place.position.row,
            place.position.column,
            place.price
        ),
        modifier = modifier
    )
}

@Composable
fun PlaceSelecting(
    places: List<List<Place>>,
    onSelect: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
    ) {
        Text(
            text = "Экран",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        HorizontalDivider(modifier = Modifier.padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding))
        Text(
            text = "Ряд",
            modifier = Modifier.padding(horizontal = UIKitPaddingDefaultTokens.DefaultContentPadding)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Places(
                places = places,
                onSelect = onSelect,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
fun Places(
    places: List<List<Place>>,
    onSelect: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val maxColumns = places.maxOf { it.last().position.column }
        val placeWidth = maxWidth / (maxColumns + 1)
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            places.forEachIndexed { index, places ->
                PlacesRow(
                    row = index + 1,
                    placeSize = placeWidth,
                    places = places,
                    onSelect = onSelect,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun PlacesRow(
    row: Int,
    placeSize: Dp,
    places: List<Place>,
    onSelect: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
    ) {
        RowNumber(row, placeSize, modifier = Modifier.size(placeSize))
        places.forEach {
            Place(
                place = it,
                placeSize = placeSize,
                onSelect = { onSelect(it) },
                modifier = Modifier.size(placeSize)
            )
        }
    }
}

@Composable
fun RowNumber(
    row: Int,
    placeSize: Dp,
    modifier: Modifier = Modifier,
) {
    val fontSize = fontSizeForRowNumber(placeSize)
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = row.toString(),
            fontSize = fontSize,
            lineHeight = fontSize,
        )
    }
}


@Composable
private fun fontSizeForRowNumber(placeSize: Dp): TextUnit {
    val density = LocalDensity.current
    val localSize = LocalTextStyle.current
    val placeSizePixels = with(density) { (placeSize - 15.dp).toSp().toPx() }
    val minSizePixels = with(density) { localSize.fontSize.toPx() }
    return with(density) { maxOf(placeSizePixels, minSizePixels).toSp() }
}

@Composable
fun Place(
    place: Place,
    placeSize: Dp,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val color by animateColorAsState(
        targetValue = colorByPlaceState(place.state),
        label = "place_color"
    )
    val isSelected = place.state == Place.PlaceState.SELECTED
    val fontSize = fontSizeForRowNumber(placeSize)
    val padding by animateDpAsState(
        targetValue = if (isSelected) 2.dp else 10.dp,
        label = "place_padding"
    )
    val corners by animateDpAsState(
        targetValue = if (isSelected) 16.dp else 8.dp,
        label = "place_corners"
    )
    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onSelect,
            )
            .padding(padding)
            .background(color, shape = RoundedCornerShape(corners)),
        contentAlignment = Alignment.Center,
    ) {
        AnimatedVisibility(
            visible = place.state == Place.PlaceState.SELECTED,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
        ) {
            Text(
                text = place.position.column.toString(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = fontSize,
                lineHeight = fontSize,
            )
        }
    }
}

@Composable
fun ZoomControls(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(UIKitPaddingDefaultTokens.DefaultItemsBetweenSpace)
    ) {
        OutlinedIconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                UIKitIconPack.Plus,
                contentDescription = null,
            )
        }
        OutlinedIconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(50.dp)
        ) {
            Icon(
                UIKitIconPack.Minus,
                contentDescription = null,
            )
        }
    }
}

@Composable
fun colorByPlaceState(
    placeState: Place.PlaceState,
): Color {
    return when (placeState) {
        Place.PlaceState.UNSELECTED -> MaterialTheme.colorScheme.surfaceVariant
        Place.PlaceState.EXTERNAL_SELECTED -> MaterialTheme.colorScheme.outline
        Place.PlaceState.SELECTED -> MaterialTheme.colorScheme.primaryContainer
    }
}