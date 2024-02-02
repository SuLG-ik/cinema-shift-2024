package ru.sulgik.core.validation.user

interface UserInputFormatter {

    fun formatFirstName(value: String): String

    fun formatLastName(value: String): String

    fun formatMiddleName(value: String): String

    fun formatPhone(value: String): String

    fun formatCode(value: String): String

}