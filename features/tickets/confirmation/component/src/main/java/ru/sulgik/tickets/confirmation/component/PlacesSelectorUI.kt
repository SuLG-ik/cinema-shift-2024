package ru.sulgik.tickets.confirmation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.sulgik.tickets.confirmation.ui.ConfirmationScreen

@Composable
fun ConfirmationUI(
    component: Confirmation,
    modifier: Modifier = Modifier,
) {
    ConfirmationScreen(
        confirmation = component.state.toUI(),
        onContinue = component::onContinue,
        onBack = component::onBack,
        modifier = modifier,
    )
}

private fun Confirmation.ConfirmationData.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation {
    return ru.sulgik.tickets.confirmation.ui.Confirmation(
        film = film.toUI(),
        seance = seance.toUI(),
        place = place.toUI(),
        order = order.toUI(),
    )
}

private fun Confirmation.ConfirmationData.OrderInfo.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.OrderInfo {
    return ru.sulgik.tickets.confirmation.ui.Confirmation.OrderInfo(totalCost)
}

private fun Confirmation.ConfirmationData.PlaceInfo.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.PlaceInfo {
    return ru.sulgik.tickets.confirmation.ui.Confirmation.PlaceInfo(places.map { it.toUI() })
}

private fun Confirmation.ConfirmationData.Place.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.Place {
    return ru.sulgik.tickets.confirmation.ui.Confirmation.Place(
        row = row,
        column = column,
    )
}

private fun Confirmation.ConfirmationData.SeanceInfo.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.SeanceInfo {
    return ru.sulgik.tickets.confirmation.ui.Confirmation.SeanceInfo(date, time, hallType.toUI())
}

private fun Confirmation.ConfirmationData.HallType.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.HallType {
    return when (this) {
        Confirmation.ConfirmationData.HallType.GREEN -> ru.sulgik.tickets.confirmation.ui.Confirmation.HallType.GREEN
        Confirmation.ConfirmationData.HallType.RED -> ru.sulgik.tickets.confirmation.ui.Confirmation.HallType.RED
        Confirmation.ConfirmationData.HallType.BLUE -> ru.sulgik.tickets.confirmation.ui.Confirmation.HallType.BLUE
        Confirmation.ConfirmationData.HallType.UNKNOWN -> ru.sulgik.tickets.confirmation.ui.Confirmation.HallType.UNKNOWN
    }
}

private fun Confirmation.ConfirmationData.FilmInfo.toUI(): ru.sulgik.tickets.confirmation.ui.Confirmation.FilmInfo {
    return ru.sulgik.tickets.confirmation.ui.Confirmation.FilmInfo(title)
}
