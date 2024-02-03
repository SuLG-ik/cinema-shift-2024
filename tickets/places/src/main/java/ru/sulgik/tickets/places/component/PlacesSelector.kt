package ru.sulgik.tickets.places.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.places.domain.entity.PlaceWithState


interface PlacesSelector {

    val isContinueAvailable: Value<Boolean>

    val places: Value<List<List<PlaceWithState>>>

    val selectedPlaces: Value<SelectedPlaces>

    fun onBack()

    fun onContinue()

    fun onSelect(placeWithState: PlaceWithState)

}