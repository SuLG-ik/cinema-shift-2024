package ru.sulgik.tickets.confirmation.component

import ru.sulgik.tickets.confirmation.domain.entity.ConfirmationData

class ConfirmationComponent(
    private val onBack: () -> Unit,
    private val onContinue: () -> Unit,
    override val state: ConfirmationData,
) : Confirmation {

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        onContinue.invoke()
    }

}