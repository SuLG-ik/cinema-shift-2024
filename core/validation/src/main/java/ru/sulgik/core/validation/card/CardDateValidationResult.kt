package ru.sulgik.core.validation.card

data class CardDateValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: CardDataError?,
)