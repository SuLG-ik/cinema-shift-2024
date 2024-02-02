package ru.sulgik.core.validation.card

data class CardNumberValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: CardDataError?,
)