package ru.sulgik.core.validation.user

class RegexUserInputValidator : UserInputValidator {

    companion object {
        const val MAX_LENGTH = 60
    }

    override fun validateFirstName(value: String): UserFirstNameValidationResult {
        if (value.isEmpty() || value.length > MAX_LENGTH) {
            return UserFirstNameValidationResult(
                value = value,
                error = UserInputError.IncorrectLength,
            )
        }
        if (USER_LATIN_LANGUAGES.containsMatchIn(value) && USER_CYRILLIC_LANGUAGES.containsMatchIn(
                value
            )
        ) {
            return UserFirstNameValidationResult(
                value = value,
                error = UserInputError.DifferentLanguages,
            )
        }
        if (value.matches(USER_LATIN_REQUIRED) || value.matches(USER_CYRILLIC_REQUIRED)) {
            return UserFirstNameValidationResult(
                value = value,
                error = null,
            )
        }
        return UserFirstNameValidationResult(
            value = value,
            error = UserInputError.IncorrectInput,
        )
    }

    override fun validateLastName(value: String): UserLastNameValidationResult {
        if (value.isEmpty() || value.length > MAX_LENGTH) {
            return UserLastNameValidationResult(
                value = value,
                error = UserInputError.IncorrectLength,
            )
        }
        if (USER_LATIN_LANGUAGES.containsMatchIn(value) && USER_CYRILLIC_LANGUAGES.containsMatchIn(
                value
            )
        ) {
            return UserLastNameValidationResult(
                value = value,
                error = UserInputError.DifferentLanguages,
            )
        }
        if (value.matches(USER_LATIN_REQUIRED) || value.matches(USER_CYRILLIC_REQUIRED)) {
            return UserLastNameValidationResult(
                value = value,
                error = null,
            )
        }
        return UserLastNameValidationResult(
            value = value,
            error = UserInputError.IncorrectInput,
        )
    }

    override fun validateMiddleName(value: String): UserMiddleNameValidationResult {
        if (value.length > MAX_LENGTH) {
            return UserMiddleNameValidationResult(
                value = value,
                error = UserInputError.IncorrectLength,
            )
        }

        if (USER_LATIN_LANGUAGES.containsMatchIn(value) && USER_CYRILLIC_LANGUAGES.containsMatchIn(
                value
            )
        ) {
            return UserMiddleNameValidationResult(
                value = value,
                error = UserInputError.DifferentLanguages,
            )
        }
        if (value.matches(USER_LATIN_REQUIRED) || value.matches(USER_CYRILLIC_REQUIRED)) {
            return UserMiddleNameValidationResult(
                value = value,
                error = null,
            )
        }
        return UserMiddleNameValidationResult(
            value = value,
            error = UserInputError.IncorrectInput,
        )
    }

    override fun validatePhone(value: String): UserPhoneValidationResult {
        if (value.isEmpty()) {
            return UserPhoneValidationResult(
                value = value,
                error = UserInputError.IncorrectLength
            )
        }
        if (value.matches(USER_PHONE)) {
            return UserPhoneValidationResult(
                value = value,
                error = null,
            )
        }
        return UserPhoneValidationResult(
            value = value,
            error = UserInputError.IncorrectInput
        )
    }

    override fun validateCode(value: String): UserCodeValidationResult {
        if (value.isEmpty()) {
            return UserCodeValidationResult(
                value = value,
                error = UserInputError.IncorrectLength
            )
        }
        if (value.matches(CODE)) {
            return UserCodeValidationResult(
                value = value,
                error = null,
            )
        }
        return UserCodeValidationResult(
            value = value,
            error = UserInputError.IncorrectLength,
        )
    }

}