package ru.sulgik.core.validation.card

data class CardCCVValidationResult(
    val value: String,
    val isFull: Boolean,
    val error: CardDataError?,
)