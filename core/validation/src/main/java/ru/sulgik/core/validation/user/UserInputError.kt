package ru.sulgik.core.validation.user


sealed interface UserInputError {
    data object IncorrectLength : UserInputError
    data object IncorrectInput : UserInputError
    data object DifferentLanguages : UserInputError
}