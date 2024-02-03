package ru.sulgik.filminfo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ru.sulgik.filminfo.component.FilmInfo

@Composable
fun FilmInfoUI(
    component: FilmInfo,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()
    FilmScreen(
        film = state.film,
        onSchedule = component::onSchedule,
        onBack = component::onBack,
        modifier = modifier,
    )
}