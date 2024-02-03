package ru.sulgik.tickets.places.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.places.component.PlacesSelector

@Composable
fun PlacesSelectorUI(
    component: PlacesSelector,
    modifier: Modifier = Modifier,
) {
    val places by component.places.subscribeAsState()
    val selectedPlaces by component.selectedPlaces.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    PlacesScreen(
        places = places,
        selectedPlaces = selectedPlaces,
        isContinueAvailable = isContinueAvailable,
        onContinue = component::onContinue,
        onBack = component::onBack,
        onSelect = component::onSelect,
        modifier = modifier
    )
}