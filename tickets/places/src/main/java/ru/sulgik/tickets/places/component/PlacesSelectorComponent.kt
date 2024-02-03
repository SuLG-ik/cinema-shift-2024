package ru.sulgik.tickets.places.component

import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.decompose.value.subscribe
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedPlaces
import ru.sulgik.tickets.domain.entity.SelectedSeance
import ru.sulgik.tickets.places.domain.entity.PlaceWithState
import ru.sulgik.tickets.places.presentation.PlacesSelectorStore
import ru.sulgik.tickets.presentation.FilmScheduleStore

class PlacesSelectorComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onContinue: (SelectedPlaces) -> Unit,
    private val selectedSeance: SelectedSeance,
    scheduleStore: FilmScheduleStore,
) : AppComponentContext(componentContext), PlacesSelector {

    private val store: PlacesSelectorStore = getStore()

    private val state = store.values(this)

    init {
        scheduleStore.values(this)
            .subscribe(lifecycle) {
                val schedule = it.schedule
                if (schedule != null)
                    store.accept(
                        PlacesSelectorStore.Intent.UpdatePlaceList(
                            seance = selectedSeance,
                            schedule = schedule
                        )
                    )
            }
    }

    override val isContinueAvailable: Value<Boolean> = state.map { it.isContinueAvailable }

    override val places: Value<List<List<PlaceWithState>>> = state.map { it.placeWithState }

    override val selectedPlaces: Value<SelectedPlaces> = state.map { it.selectedPlaces }


    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        if (store.state.isContinueAvailable)
            onContinue.invoke(
                SelectedPlaces(
                    totalCost = store.state.selectedPlaces.totalCost,
                    places = store.state.selectedPlaces.places.map {
                        SelectedPlaces.Place(
                            price = it.price, row = it.row, column = it.column
                        )

                    })
            )
    }

    override fun onSelect(placeWithState: PlaceWithState) {
        store.accept(PlacesSelectorStore.Intent.Select(placeWithState))
    }


}

private fun String.toState(): Schedule.HallType {
    return when (this) {
        "Red" -> Schedule.HallType.RED
        "Green" -> Schedule.HallType.GREEN
        "Blue" -> Schedule.HallType.BLUE
        else -> Schedule.HallType.UNKNOWN
    }
}