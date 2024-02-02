package ru.sulgik.core.validation.card

interface CardInputFormatter {

    fun formatNumber(value: String): String

    fun formatDate(value: String): String

    fun formatCCV(value: String): String

}