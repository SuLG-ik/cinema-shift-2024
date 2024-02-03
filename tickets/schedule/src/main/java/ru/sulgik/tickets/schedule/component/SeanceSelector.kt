package ru.sulgik.tickets.schedule.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.core.component.ValueContainer
import ru.sulgik.tickets.domain.entity.Schedule
import ru.sulgik.tickets.domain.entity.SelectedSeance


interface SeanceSelector {

    val schedule: Value<List<Schedule>>

    val isContinueAvailable: Value<Boolean>

    val selectedSeance: Value<ValueContainer<SelectedSeance?>>

    fun onSelect(seance: Schedule.Seance)

    fun onBack()

    fun onContinue()

}