package ru.sulgik.core.validation.user

import ru.sulgik.core.validation.card.sliceIfAvailable

class RawUserInputFormatter : UserInputFormatter {

    override fun formatFirstName(value: String): String {
        return value.trimStart()
    }

    override fun formatLastName(value: String): String {
        return value.trimStart()
    }

    override fun formatMiddleName(value: String): String {
        return value.trimStart()
    }

    override fun formatPhone(value: String): String {
        return value.filter(Char::isDigit).sliceIfAvailable(0 until  10)
    }
    override fun formatCode(value: String): String {
        return value.filter(Char::isDigit).sliceIfAvailable(0 until  6)
    }

}