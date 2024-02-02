package ru.sulgik.core.validation.card

internal class RawCardInputFormatter : CardInputFormatter {

    override fun formatNumber(value: String): String {
        return value.filter { it.isDigit() }.sliceIfAvailable(0 until 8)
    }

    override fun formatDate(value: String): String {
        return value.filter { it.isDigit() }.sliceIfAvailable(0 until 4)

    }

    override fun formatCCV(value: String): String {
        return value.filter { it.isDigit() }.sliceIfAvailable(0 until 4)
    }

}

fun String.sliceIfAvailable(indices: IntRange): String {
    if (length <= indices.last + 1)
        return this
    return slice(indices)
}