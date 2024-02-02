package ru.sulgik.tickets.confirmation.component

class ConfirmationComponent(
    private val onBack: () -> Unit,
    private val onContinue: () -> Unit,
    override val state: Confirmation.ConfirmationData,
) : Confirmation {

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        onContinue.invoke()
    }

}