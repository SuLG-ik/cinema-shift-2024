package ru.sulgik.core.validation.user

val USER_CYRILLIC_REQUIRED = "[а-яА-Я `‘-]".toRegex()
val USER_LATIN_REQUIRED = "[a-zA-Z `‘-]".toRegex()
val USER_ALL_LANHUAGES_REQUIRED = "[а-яА-Яa-zA-Z `‘-]".toRegex()
val USER_PHONE = "[0-9]{10}".toRegex()