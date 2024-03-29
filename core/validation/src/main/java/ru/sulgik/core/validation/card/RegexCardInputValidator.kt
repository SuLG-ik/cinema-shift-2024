package ru.sulgik.core.validation.card

internal class RegexCardInputValidator : CardInputValidator {

    override fun validateNumber(value: String): CardNumberValidationResult {
        if (value.length != 8) {
            return CardNumberValidationResult(
                value,
                isFull = false,
                error = CardDataError.IncorrectLength,
            )
        }
        if (value.matches(CARD_NUMBER_REGEX)) {
            return CardNumberValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }
        return CardNumberValidationResult(
            value = value,
            isFull = true,
            error = CardDataError.IncorrectValue,
        )
    }

    override fun validateDate(value: String): CardDateValidationResult {
        if (value.length != 4) {
            return CardDateValidationResult(
                value = value,
                isFull = false,
                error = CardDataError.IncorrectLength
            )
        }
        if (value.matches(CARD_DATE_REGEX_STARTS_ZERO) || value.matches(CARD_DATE_REGEX_STARTS_ONE)) {
            return CardDateValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }

        return CardDateValidationResult(
            value = value,
            isFull = true,
            error = CardDataError.IncorrectValue
        )
    }

    override fun validateCCV(value: String): CardCCVValidationResult {
        if (value.length != 4) {
            return CardCCVValidationResult(
                value = value,
                isFull = false,
                error = CardDataError.IncorrectLength,
            )
        }
        if (value.matches(CARD_CCV_REGEX)) {
            return CardCCVValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }
        return CardCCVValidationResult(
            value = value,
            isFull = true,
            error = CardDataError.IncorrectValue
        )
    }

}