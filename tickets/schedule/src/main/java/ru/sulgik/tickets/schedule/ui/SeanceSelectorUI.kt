package ru.sulgik.tickets.schedule.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.sulgik.tickets.schedule.component.SeanceSelector

@Composable
fun SeanceSelectorUI(
    component: SeanceSelector,
    modifier: Modifier = Modifier,
) {
    val schedule by component.schedule.subscribeAsState()
    val isContinueAvailable by component.isContinueAvailable.subscribeAsState()
    val selectedSeance by component.selectedSeance.subscribeAsState()
    ScheduleScreen(
        seances = schedule,
        isContinueAvailable = isContinueAvailable,
        selectedSeance = selectedSeance.value,
        onBack = component::onBack,
        onContinue = component::onContinue,
        onSelect = component::onSelect,
        modifier = modifier,
    )
}