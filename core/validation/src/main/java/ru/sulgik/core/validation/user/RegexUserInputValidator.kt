package ru.sulgik.core.validation.user

class RegexUserInputValidator : UserInputValidator {

    override fun validateFirstName(value: String): UserFirstNameValidationResult {
        if (value.isEmpty()) {
            return UserFirstNameValidationResult(
                value = value,
                isFull = false,
                error = UserFirstNameValidationResult.Error.IncorrectLength,
            )
        }
        if (!value.matches(USER_ALL_LANHUAGES_REQUIRED)) {
            return UserFirstNameValidationResult(
                value = value,
                isFull = true,
                error = UserFirstNameValidationResult.Error.DifferentLanguages,
            )
        }
        if (value.matches(USER_LATIN_REQUIRED) || value.matches(USER_CYRILLIC_REQUIRED)) {
            return UserFirstNameValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }
        return UserFirstNameValidationResult(
            value = value,
            isFull = true,
            error = UserFirstNameValidationResult.Error.IncorrectInput,
        )
    }

    override fun validateLastName(value: String): UserLastNameValidationResult {
        if (value.isEmpty()) {
            return UserLastNameValidationResult(
                value = value,
                isFull = false,
                error = UserLastNameValidationResult.Error.IncorrectLength,
            )
        }
        if (!value.matches(USER_ALL_LANHUAGES_REQUIRED)) {
            return UserLastNameValidationResult(
                value = value,
                isFull = true,
                error = UserLastNameValidationResult.Error.DifferentLanguages,
            )
        }
        if (value.matches(USER_LATIN_REQUIRED) || value.matches(USER_CYRILLIC_REQUIRED)) {
            return UserLastNameValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }
        return UserLastNameValidationResult(
            value = value,
            isFull = true,
            error = UserLastNameValidationResult.Error.IncorrectInput,
        )
    }

    override fun validateMiddleName(value: String): UserMiddleNameValidationResult {
        if (!value.matches(USER_ALL_LANHUAGES_REQUIRED)) {
            return UserMiddleNameValidationResult(
                value = value,
                error = UserMiddleNameValidationResult.Error.DifferentLanguages,
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
            error = UserMiddleNameValidationResult.Error.IncorrectInput,
        )
    }

    override fun validatePhone(value: String): UserPhoneValidationResult {
        if (value.isEmpty()) {
            return UserPhoneValidationResult(
                value = value,
                isFull = false,
                error = UserPhoneValidationResult.Error.IncorrectLength
            )
        }
        if (value.matches(USER_PHONE)) {
            return UserPhoneValidationResult(
                value = value,
                isFull = true,
                error = null,
            )
        }
        return UserPhoneValidationResult(
            value = value,
            isFull = false,
            error = UserPhoneValidationResult.Error.IncorrectInput
        )
    }

}