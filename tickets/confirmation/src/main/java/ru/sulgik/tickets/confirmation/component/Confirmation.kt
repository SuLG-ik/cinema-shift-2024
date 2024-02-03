package ru.sulgik.tickets.confirmation.component

import ru.sulgik.tickets.confirmation.domain.entity.ConfirmationData


interface Confirmation {

    val state: ConfirmationData

    fun onBack()

    fun onContinue()

}