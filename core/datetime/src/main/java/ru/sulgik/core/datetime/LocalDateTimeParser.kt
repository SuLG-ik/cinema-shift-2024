package ru.sulgik.core.datetime

import androidx.compose.runtime.staticCompositionLocalOf

val LocalDateTimeFormatter = staticCompositionLocalOf<DateTimeFormatter> { error("LocalDateTimeParser does not provided") }