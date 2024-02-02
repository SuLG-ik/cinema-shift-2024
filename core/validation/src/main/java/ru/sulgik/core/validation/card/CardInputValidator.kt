package ru.sulgik.core.validation.card

interface CardInputValidator {

    fun validateNumber(value: String): CardNumberValidationResult

    fun validateDate(value: String): CardDateValidationResult

    fun validateCCV(value: String): CardCCVValidationResult

}