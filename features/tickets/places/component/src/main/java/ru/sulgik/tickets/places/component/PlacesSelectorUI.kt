package ru.sulgik.tickets.places.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.places.ui.Place
import ru.sulgik.tickets.places.ui.PlacesScreen

@Composable
fun PlacesSelectorUI(
    component: PlacesSelector,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()
    PlacesScreen(
        places = state.places.map { it.map { it.toUI() } },
        selectedPlaces = state.selectedPlaces.map { it.toUI() },
        totalCost = state.totalCost,
        isContinueAvailable = state.isContinueAvailable,
        onContinue = component::onContinue,
        onBack = component::onBack,
        onSelect = {
            if (it.state == Place.PlaceState.SELECTED)
                component.onUnselect(it.position.row, it.position.column)
            else if (it.state == Place.PlaceState.UNSELECTED)
                component.onSelect(it.position.row, it.position.column, it.price)
        },
        modifier = modifier
    )
}

private fun PlacesSelector.State.Place.toUI(): Place {
    return Place(
        state = state.toUI(), price = cost, position = position.toUI()
    )
}

private fun PlacesSelector.State.Position.toUI(): Place.Position {
    return Place.Position(
        row = row + 1,
        column = column + 1,
    )
}

private fun PlacesSelector.State.PlaceState.toUI(): Place.PlaceState {
    return when (this) {
        PlacesSelector.State.PlaceState.UNSELECTED -> Place.PlaceState.UNSELECTED
        PlacesSelector.State.PlaceState.EXTERNAL_SELECTED -> Place.PlaceState.EXTERNAL_SELECTED
        PlacesSelector.State.PlaceState.SELECTED -> Place.PlaceState.SELECTED
    }
}
