package ru.sulgik.core.validation.card

val CARD_DATE_REGEX_STARTS_ZERO = "0[0-9]{3}".toRegex()
val CARD_DATE_REGEX_STARTS_ONE = "1[0-2][0-9]{2}".toRegex()

val CARD_NUMBER_REGEX = "[0-9]{8}".toRegex()
val CARD_CCV_REGEX = "[0-9]{4}".toRegex()