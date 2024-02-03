package ru.sulgik.profile.edit.component

class ConfirmationComponent(
    private val onBack: () -> Unit,
    private val onContinue: () -> Unit,
) : Confirmation {

    override fun onBack() {
        onBack.invoke()
    }

    override fun onContinue() {
        onContinue.invoke()
    }

}