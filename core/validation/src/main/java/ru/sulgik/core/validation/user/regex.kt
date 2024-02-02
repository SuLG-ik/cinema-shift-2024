package ru.sulgik.core.validation.user

val USER_CYRILLIC_REQUIRED = "[а-яА-Я\\s`‘-]*".toRegex()
val USER_LATIN_REQUIRED = "[a-zA-Z\\s`‘-]*".toRegex()
val USER_LATIN_LANGUAGES = "[a-zA-Z]".toRegex()
val USER_CYRILLIC_LANGUAGES = "[а-яА-Я]".toRegex()
val USER_PHONE = "[0-9]{10}".toRegex()
val CODE = "[0-9]{6}".toRegex()