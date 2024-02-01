package ru.sulgik.tickets.places.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.AppComponentContext
import ru.sulgik.core.component.DIComponentContext
import ru.sulgik.core.component.getStore
import ru.sulgik.core.component.values
import ru.sulgik.core.component.zip
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.places.presentation.PlacesSelectorStore
import ru.sulgik.tickets.presentation.FilmScheduleStore

class PlacesSelectorComponent(
    componentContext: DIComponentContext,
    private val onBack: () -> Unit,
    private val onContinue: (PlacesSelector.SelectedPlaces) -> Unit,
    private val selectedSeance: PlacesSelector.SelectedSeance,
    scheduleStore: FilmScheduleStore,
) : AppComponentContext(componentContext), PlacesSelector {

    private val store: PlacesSelectorStore = getStore()

    private val scheduleState = scheduleStore.values(this)
    private val seanceSelectorState = store.values(this)

    private var currentSeance: Schedule.Seance? = null

    override val state: Value<PlacesSelector.State> =
        zip(this, scheduleState, seanceSelectorState, this::convert)

    private fun convert(
        first: FilmScheduleStore.State,
        second: PlacesSelectorStore.State
    ): PlacesSelector.State {
        val schedule = first.schedule
            ?: return PlacesSelector.State(
                isLoading = true,
                isContinueAvailable = false,
                places = emptyList(),
                totalCost = second.totalCost,
                selectedPlaces = emptyList()
            )
        val seance = getAndSaveCurrentSeance(schedule)
        return PlacesSelector.State(
            isLoading = first.isLoading,
            isContinueAvailable = second.isContinueAvailable,
            places = convert(seance.hall.places, second.selectedPlaces),
            totalCost = second.totalCost,
            selectedPlaces = second.selectedPlaces.map {
                PlacesSelector.State.Place(
                    state = PlacesSelector.State.PlaceState.SELECTED,
                    cost = it.price,
                    position = PlacesSelector.State.Position(
                        row = it.row,
                        column = it.column
                    )
                )
            }
        )
    }

    private fun convert(
        places: List<List<Schedule.Place>>,
        selectedPlaces: List<PlacesSelectorStore.State.SelectedPlace>
    ): List<List<PlacesSelector.State.Place>> {
        return places.mapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, column ->
                PlacesSelector.State.Place(
                    state = convertToType(
                        isExternalSelected = column.type == Schedule.FilmHallCellType.BLOCKED,
                        isOrderSelected = selectedPlaces.any { it.column == columnIndex + 1 && it.row == rowIndex + 1 },
                    ),
                    cost = column.price,
                    position = PlacesSelector.State.Position(
                        row = rowIndex,
                        column = columnIndex
                    )
                )
            }
        }
    }

    private fun getAndSaveCurrentSeance(schedule: List<Schedule>): Schedule.Seance {
        return currentSeance ?: run {
            val date = schedule.find { it.date == selectedSeance.date }
                ?: throw IllegalStateException("Date ${selectedSeance.date} for places selection not found")
            val time =
                date.seances.find { it.time == selectedSeance.time && it.hall.name.toState() == selectedSeance.hallType }
                    ?: throw IllegalStateException("Date ${selectedSeance.date} for places selection not found")
            currentSeance = time
            time
        }
    }

    fun convertToType(
        isExternalSelected: Boolean,
        isOrderSelected: Boolean
    ): PlacesSelector.State.PlaceState {
        return when {
            isExternalSelected -> PlacesSelector.State.PlaceState.EXTERNAL_SELECTED
            isOrderSelected -> PlacesSelector.State.PlaceState.SELECTED
            else -> PlacesSelector.State.PlaceState.UNSELECTED
        }
    }

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        if (store.state.isContinueAvailable)
            onContinue.invoke(
                PlacesSelector.SelectedPlaces(
                    totalCost = store.state.totalCost,
                    places = store.state.selectedPlaces.map {
                        PlacesSelector.SelectedPlace(
                            it.row, it.column
                        )

                    })
            )
    }

    override fun onSelect(row: Int, column: Int, price: Int) {
        store.accept(PlacesSelectorStore.Intent.Select(row, column, price))
    }

    override fun onUnselect(row: Int, column: Int) {
        store.accept(PlacesSelectorStore.Intent.Unselect(row, column))
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