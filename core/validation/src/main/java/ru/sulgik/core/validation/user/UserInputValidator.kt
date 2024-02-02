package ru.sulgik.core.validation.user

interface UserInputValidator {

    fun validateFirstName(value: String): UserFirstNameValidationResult

    fun validateLastName(value: String): UserLastNameValidationResult

    fun validateMiddleName(value: String): UserMiddleNameValidationResult

    fun validatePhone(value: String): UserPhoneValidationResult

    fun validateCode(value: String): UserCodeValidationResult

}