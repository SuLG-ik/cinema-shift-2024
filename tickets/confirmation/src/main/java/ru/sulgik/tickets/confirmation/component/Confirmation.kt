package ru.sulgik.tickets.confirmation.component

import com.arkivanov.decompose.value.Value
import ru.sulgik.tickets.confirmation.domain.entity.ConfirmationData
import java.time.LocalDate
import java.time.LocalTime


interface Confirmation {

    val state: ConfirmationData

    fun onBack()

    fun onContinue()

}